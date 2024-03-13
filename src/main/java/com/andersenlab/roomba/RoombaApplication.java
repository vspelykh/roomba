package com.andersenlab.roomba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class RoombaApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoombaApplication.class, args);
    }

}
