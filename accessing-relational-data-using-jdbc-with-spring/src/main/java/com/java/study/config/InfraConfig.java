package com.java.study.config;

import com.java.study.infra.CrudCustomerRepository;
import com.java.study.infra.DataJdbcCustomerRepository;
import com.java.study.infra.JdbcTemplateCustomerRepository;
import com.java.study.infra.PureJdbcCustomerRepository;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class InfraConfig {

    @Bean
    public PureJdbcCustomerRepository pureJdbcCustomerRepository(DataSource dataSource) {
        return new PureJdbcCustomerRepository(dataSource);
    }

    @Bean
    public JdbcTemplateCustomerRepository jdbcTemplateCustomerRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcTemplateCustomerRepository(jdbcTemplate);
    }

    @Bean
    public DataJdbcCustomerRepository dataJdbcCustomerRepository(CrudCustomerRepository crudCustomerRepository) {
        return new DataJdbcCustomerRepository(crudCustomerRepository);
    }
}
