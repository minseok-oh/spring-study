package com.java.study.customer;

import java.util.List;

public interface CustomerRepository {
    Customer insert(Customer customer);
    List<Customer> selectByFirstName(String firstName);
}
