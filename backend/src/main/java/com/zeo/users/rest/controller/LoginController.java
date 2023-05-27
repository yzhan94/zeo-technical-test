package com.zeo.users.rest.controller;

import com.zeo.users.rest.model.Credentials;
import com.zeo.users.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final UserService userService;

    public LoginController(final @Autowired UserService userService) {
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(final Credentials credentials) {
        if (!isValid(credentials)) {
            return ResponseEntity.badRequest().build();
        }

        final boolean loginOk = userService.findUser(credentials.getEmail(), credentials.getPassword())
                .isPresent();

        if (loginOk) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    private boolean isValid(Credentials credentials) {
        return credentials != null
                && StringUtils.isNotBlank(credentials.getEmail())
                && StringUtils.isNotBlank(credentials.getPassword());
    }
}
