package com.java.study.infra;

import com.java.study.customer.Customer;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CrudCustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findCustomersByFirstName(String firstName);
}
