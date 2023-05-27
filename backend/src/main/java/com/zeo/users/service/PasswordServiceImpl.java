package com.zeo.users.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;

@Component
public class PasswordServiceImpl implements PasswordService {

    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public String hash(final String password) {
        return encoder.encode(password);
    }

    @Override
    public boolean matches(final String password, final String hash) {
        return encoder.matches(password, hash);
    }
}
