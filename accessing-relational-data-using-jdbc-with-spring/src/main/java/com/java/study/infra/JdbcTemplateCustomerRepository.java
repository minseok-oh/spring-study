package com.java.study.infra;

import com.java.study.customer.Customer;
import com.java.study.customer.CustomerRepository;
import java.sql.PreparedStatement;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcTemplateCustomerRepository implements CustomerRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateCustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Customer insert(Customer customer) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String insertQuery = "INSERT INTO Customer(firstname, lastname) VALUES (?,?)";

        jdbcTemplate.update((connection) -> {
            PreparedStatement pstmt = connection.prepareStatement(insertQuery, new String[]{"id"});
            pstmt.setString(1, customer.getFirstName());
            pstmt.setString(2, customer.getLastName());
            return pstmt;
        }, keyHolder);

        return new Customer((Long) keyHolder.getKey(), customer.getFirstName(), customer.getLastName());
    }

    @Override
    public List<Customer> selectByFirstName(String firstName) {
        return jdbcTemplate.query("SELECT id, firstname, lastname FROM Customer WHERE firstname = ?",
                (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("firstname"), rs.getString("lastname")),
                firstName);
    }
}
