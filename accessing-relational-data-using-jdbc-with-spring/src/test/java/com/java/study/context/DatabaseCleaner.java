package com.java.study.context;

import com.java.study.customer.Customer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class DatabaseCleaner implements InitializingBean {

    private static final String DB_URL = "jdbc:h2:mem:testdb";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    List<String> tableNames = new ArrayList<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        tableNames.add(Customer.class.getSimpleName());
    }

    public void clear() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String truncateQuery = "TRUNCATE TABLE ";
            Statement statement = connection.createStatement();

            for (String table: tableNames) {
                statement.execute(truncateQuery + table);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
