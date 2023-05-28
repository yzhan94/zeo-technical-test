package com.zeo.users.service.impl;

import com.zeo.users.ZeoUsersApplication;
import com.zeo.users.exception.UserExistsException;
import com.zeo.users.rest.model.User;
import com.zeo.users.rest.model.UserWithPassword;
import com.zeo.users.service.PasswordService;
import com.zeo.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * Servicio de usuarios volatiles
 */
@Component
@ConditionalOnProperty(value = "persistence.mode", havingValue = ZeoUsersApplication.PERSISTENCE_VOLATILE, matchIfMissing = true)
public class InMemoryUserServiceImpl implements UserService {

    private final ConcurrentMap<String, InMemoryUser> usersMap = new ConcurrentHashMap<>();
    private final Object checkUserLock = new Object();
    private final PasswordService passwordService;

    public InMemoryUserServiceImpl(final @Autowired PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @Override
    public List<User> getAllUsers() {
        return usersMap.entrySet().stream() //
                .map(entry -> convertToUser(entry.getKey(), entry.getValue())) //
                .collect(Collectors.toList());
    }

    @Override
    public void createUser(final User user, final String password) throws UserExistsException {
        synchronized (checkUserLock) {
            if (usersMap.containsKey(user.getEmail())) {
                throw new UserExistsException();
            }
            final InMemoryUser userData = convertFromUser(user, password);
            usersMap.put(user.getEmail(), userData);
        }
    }

    @Override
    public Optional<User> findUser(final String email, final String password) {
        final InMemoryUser user = usersMap.get(email);
        if (user != null && passwordService.matches(password, user.getPasswordHash())) {
            return Optional.of(convertToUser(email, user));
        }
        return Optional.empty();
    }

    private User convertToUser(final String email, final InMemoryUser inMemoryUser) {
        final User user = new User();
        user.setName(inMemoryUser.getName());
        user.setAge(inMemoryUser.getAge());
        user.setEmail(email);
        return user;
    }

    private InMemoryUser convertFromUser(final User user, final String password) {
        final InMemoryUser userData = new InMemoryUser();
        userData.setName(user.getName());
        userData.setAge(user.getAge());
        userData.setPasswordHash(passwordService.hash(password));
        return userData;
    }

    private class InMemoryUser {
        private String name;
        private Integer age;
        private String passwordHash;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getPasswordHash() {
            return passwordHash;
        }

        public void setPasswordHash(String passwordHash) {
            this.passwordHash = passwordHash;
        }
    }
}
