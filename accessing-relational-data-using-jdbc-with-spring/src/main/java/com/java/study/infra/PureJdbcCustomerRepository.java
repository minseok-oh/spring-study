package com.java.study.infra;

import com.java.study.customer.Customer;
import com.java.study.customer.CustomerRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.stereotype.Repository;

@Repository
public class PureJdbcCustomerRepository implements CustomerRepository {

    private final DataSource dataSource;

    public PureJdbcCustomerRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Customer insert(Customer customer) {
        String insertQuery = "INSERT INTO Customer(firstname, lastname) VALUES (?,?)";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(insertQuery, new String[]{"id"})
        ) {

            pstmt.setString(1, customer.getFirstName());
            pstmt.setString(2, customer.getLastName());
            pstmt.executeUpdate();

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                return new Customer(generatedKeys.getLong(1), customer.getFirstName(), customer.getLastName());
            } else {
                throw new SQLException("생성된 Id가 존재하지 않습니다.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Customer> selectByFirstName(String firstName) {
        String selectQuery = "SELECT id, firstname, lastname FROM Customer WHERE firstname = ?";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(selectQuery)
        ) {
            pstmt.setString(1, firstName);

            ResultSet resultSet = pstmt.executeQuery();
            List<Customer> selectedCustomers = new ArrayList<>();

            while (resultSet.next()) {
                Long selectedId = resultSet.getLong("id");
                String selectedFirstName = resultSet.getString("firstname");
                String selectedLastName = resultSet.getString("lastName");

                selectedCustomers.add(new Customer(selectedId, selectedFirstName, selectedLastName));
            }
            return selectedCustomers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
