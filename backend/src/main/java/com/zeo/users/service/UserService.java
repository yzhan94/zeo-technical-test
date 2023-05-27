package com.zeo.users.service;

import com.zeo.users.exception.UserExistsException;
import com.zeo.users.rest.model.User;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Metodos de servicios de usuarios
 */
@Service
public interface UserService {
    List<User> getAllUsers();

    void createUser(final User user, final String password) throws UserExistsException;

    Optional<User> findUser(final String email, final String password);

}
