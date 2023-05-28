package com.zeo.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ZeoUsersApplication {

    public static final String PERSISTENCE_VOLATILE = "VOLATILE";
    public static final String PERSISTENCE_PERSISTENT = "PERSISTENT";

    public static void main(String[] args) {
        SpringApplication.run(ZeoUsersApplication.class, args);
    }

}
