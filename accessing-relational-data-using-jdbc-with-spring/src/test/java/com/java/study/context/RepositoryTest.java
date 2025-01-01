package com.java.study.context;

import com.java.study.config.DatabaseConfig;
import com.java.study.infra.CrudCustomerRepository;
import javax.sql.DataSource;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class RepositoryTest {

    @Autowired
    protected DataSource dataSource;

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Autowired
    protected CrudCustomerRepository crudCustomerRepository;

    @Autowired
    protected DatabaseCleaner databaseCleaner;

    @Autowired
    protected DatabaseConfig databaseConfig;

    @AfterEach
    void afterEach() {
        databaseCleaner.clear();
    }
}
