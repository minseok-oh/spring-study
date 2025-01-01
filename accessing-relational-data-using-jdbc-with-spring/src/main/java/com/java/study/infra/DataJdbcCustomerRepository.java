package com.java.study.infra;

import com.java.study.customer.Customer;
import com.java.study.customer.CustomerRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class DataJdbcCustomerRepository implements CustomerRepository {

    private final CrudCustomerRepository repository;

    public DataJdbcCustomerRepository(CrudCustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer insert(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public List<Customer> selectByFirstName(String firstName) {
        return repository.findCustomersByFirstName(firstName);
    }
}
