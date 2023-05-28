package com.zeo.users.rest.controller;

import com.zeo.users.exception.UserExistsException;
import com.zeo.users.rest.model.User;
import com.zeo.users.rest.model.UserWithPassword;
import com.zeo.users.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(final @Autowired UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users")
    public List<User> listUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("users")
    public ResponseEntity<?> createUser(final @RequestBody UserWithPassword user) {
        if (!isValid(user)) {
            LOGGER.atInfo().log("invalid user data {}", user.getEmail());
            return ResponseEntity.badRequest().build();
        }

        try {
            userService.createUser(user, user.getPassword());

            return ResponseEntity.noContent().build();
        } catch (final UserExistsException e) {
            LOGGER.atDebug().log("User with email {} exists", user.getEmail());
            return ResponseEntity.unprocessableEntity().build();
        }

    }

    private boolean isValid(final UserWithPassword user) {
        return user != null
                && StringUtils.isNotBlank(user.getName())
                && StringUtils.isNotBlank(user.getPassword())
                && StringUtils.isNotBlank(user.getEmail());
    }


}
