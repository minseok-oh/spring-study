package com.java.study.context;

import com.java.study.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class RepositoryTest {

    @Autowired
    protected CustomerRepository customerRepository;

    @Autowired
    protected DatabaseCleaner databaseCleaner;

    @AfterEach
    void afterEach() {
        databaseCleaner.clear();
    }
}
