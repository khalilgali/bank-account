package com.xalt.kata.service;

import com.xalt.kata.entity.Customer;
import com.xalt.kata.exception.ResourceNotFoundException;
import com.xalt.kata.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl  implements CustomerService{
    
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {

        Optional<Customer> customerToUpdate = customerRepository.findById(customer.getId());

        if(customerToUpdate.isPresent())
        {
            Customer customerUpdate = customerToUpdate.get();
            customerUpdate.setId(customer.getId());
            customerUpdate.setName(customer.getName());
            customerUpdate.setBirthDate(customer.getBirthDate());
            customerUpdate.setAddress(customer.getAddress());
            customerRepository.save(customerUpdate);
            return customerUpdate;
        }
        else{
            throw new ResourceNotFoundException("Resource not found with id : "+ customer.getId());
        }
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(long customerId) {

        Optional<Customer> customer = customerRepository.findById(customerId);

        if(customer.isPresent())
        {
            return customer.get();
        }
        else{
            throw new ResourceNotFoundException("Resource not found with id : "+ customerId);
        }
    }

    @Override
    public void deleteCustomerById(long customerId) {

        Optional<Customer> customer = customerRepository.findById(customerId);

        if(customer.isPresent())
        {
            customerRepository.delete(customer.get());
        }
        else{
            throw new ResourceNotFoundException("Resource not found with id : "+ customerId);
        }
    }
}
