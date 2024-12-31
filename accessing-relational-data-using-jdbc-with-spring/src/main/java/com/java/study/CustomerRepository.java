package com.java.study;

public interface CustomerRepository {
    Customer insert(Customer customer);
    Customer selectByFirstName(String firstName);
}
