package com.zeo.users.rest.model;

public class UserWithPassword extends User {

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
