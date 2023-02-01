package com.xalt.kata.controller;

import com.xalt.kata.entity.Account;
import com.xalt.kata.entity.Customer;
import com.xalt.kata.mapper.AccountMapper;
import com.xalt.kata.mapper.CustomerAccountDetailsMapper;
import com.xalt.kata.mapper.CustomerMapper;
import com.xalt.kata.model.AccountModel;
import com.xalt.kata.model.CustomerAccountDetailsModel;
import com.xalt.kata.model.CustomerModel;
import com.xalt.kata.service.AccountService;
import com.xalt.kata.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private CustomerAccountDetailsMapper customerAccountDetailsMapper;


    @Autowired
    private AccountService accountService;

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerModel>> getAllCustomer()
    {
        List<CustomerModel> customerModelList = customerService.getAllCustomer()
                .stream()
                .map(customer -> customerMapper.customerToCustomerModel(customer))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(customerModelList);
    }

    @PostMapping("/customers")
    public ResponseEntity<CustomerModel> createCustomer(@RequestBody CustomerModel customerModel)
    {
        Customer customer = customerMapper.customerModelToCustomer(customerModel);
        CustomerModel createdCustomer = customerMapper.customerToCustomerModel(customerService.createCustomer(customer));
        return ResponseEntity.ok().body(createdCustomer);
    }

    @PostMapping("/customers/{customer-id}/accounts")
    public ResponseEntity<AccountModel> createCustomerAccount(@PathVariable("customer-id") long customerId , @RequestBody AccountModel accountModel)
    {
        Customer customer = customerService.getCustomerById(customerId);
        Account account = accountMapper.accountModelToAccount(accountModel);
        account.setCustomer(customer);
        AccountModel createdAccount = accountMapper.accountToAccountModel(accountService.createAccount(account));
        return ResponseEntity.ok().body(createdAccount);
    }

    @GetMapping("/customers/{customer-id}/history")
    public ResponseEntity<CustomerAccountDetailsModel> getCustomerOperationHistory(@PathVariable("customer-id") long customerId)
    {
        Customer customer = customerService.getCustomerById(customerId);
        CustomerAccountDetailsModel customerAccountDetailsModel = customerAccountDetailsMapper.customerToCustomerAccountDetailsModel(customer);
        return ResponseEntity.ok().body(customerAccountDetailsModel);
    }

    @PutMapping("/customers")
    public ResponseEntity<CustomerModel> updateCustomer(@RequestBody CustomerModel customerModel)
    {
        Customer customer = customerMapper.customerModelToCustomer(customerModel);
        CustomerModel updatedCustomer = customerMapper.customerToCustomerModel(customerService.updateCustomer(customer));
        return ResponseEntity.ok().body(updatedCustomer);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerModel> getCustomerById(@PathVariable long id)
    {
        CustomerModel customerModel = customerMapper.customerToCustomerModel(customerService.getCustomerById(id));
        return ResponseEntity.ok().body(customerModel);
    }

    @DeleteMapping("/customers/{id}")
    public HttpStatus deleteCustomerById(@PathVariable long id)
    {
        customerService.deleteCustomerById(id);
        return HttpStatus.OK;
    }
}
