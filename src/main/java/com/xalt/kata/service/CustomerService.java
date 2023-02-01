package com.xalt.kata.service;

import com.xalt.kata.entity.Customer;

import java.util.List;

public interface CustomerService {

    Customer createCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
    List<Customer> getAllCustomer();
    Customer getCustomerById(long customerId);
    void deleteCustomerById(long customerId);
}
