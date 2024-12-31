package com.java.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccessingRelationalDataUsingJdbcWithSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccessingRelationalDataUsingJdbcWithSpringApplication.class, args);
    }

    @Bean
    public CustomerRepository customerRepository() {
        return null;
    }
}
