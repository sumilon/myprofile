package com.iprofile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IProfileApplication {

    public static void main(String[] args) {
        SpringApplication.run(IProfileApplication.class, args);
    }

}
