package com.zeo.users.service.impl;

import com.zeo.users.ZeoUsersApplication;
import com.zeo.users.dao.UserDaoRepository;
import com.zeo.users.exception.UserExistsException;
import com.zeo.users.model.UserDao;
import com.zeo.users.rest.model.User;
import com.zeo.users.service.PasswordService;
import com.zeo.users.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * Servicio de usuarios persistentes en BD HSQL
 */
@Component
@ConditionalOnProperty(value = "persistence.mode", havingValue = ZeoUsersApplication.PERSISTENCE_PERSISTENT)
public class PersistentUserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersistentUserServiceImpl.class);

    private final UserDaoRepository userDaoRepository;
    private final PasswordService passwordService;

    public PersistentUserServiceImpl(final @Autowired UserDaoRepository userDaoRepository, final @Autowired PasswordService passwordService) {
        this.userDaoRepository = userDaoRepository;
        this.passwordService = passwordService;
    }

    @Override
    public List<User> getAllUsers() {
        return userDaoRepository.findAll()
                .stream()
                .map(this::convertToUser)
                .collect(Collectors.toList());
    }

    @Override
    public void createUser(final User user, final String password) throws UserExistsException {
        final UserDao userDao = convertToUserDao(user, password);
        try {
            userDaoRepository.save(userDao);
        } catch (final DataIntegrityViolationException e) {
            LOGGER.atError().log("Error creating user {}", user.getEmail(), e);
            throw new UserExistsException();
        }
    }

    @Override
    public Optional<User> findUser(final String email, final String password) {
        return userDaoRepository.findByEmail(email)
                .filter(userDao -> passwordService.matches(password, userDao.getPasswordHash()))
                .map(this::convertToUser);
    }

    private User convertToUser(final UserDao userDao) {
        final User user = new User();
        user.setName(userDao.getName());
        user.setAge(userDao.getAge());
        user.setEmail(userDao.getEmail());
        return user;
    }

    private UserDao convertToUserDao(final User user, final String password) {
        final UserDao userDao = new UserDao();
        userDao.setName(user.getName());
        userDao.setAge(user.getAge());
        userDao.setEmail(user.getEmail());
        userDao.setPasswordHash(passwordService.hash(password));
        return userDao;
    }
}
