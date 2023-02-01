package com.xalt.kata;


import com.xalt.kata.entity.Account;
import com.xalt.kata.entity.Customer;
import com.xalt.kata.entity.Operation;
import com.xalt.kata.exception.InsufficientBalanceException;
import com.xalt.kata.service.AccountService;
import com.xalt.kata.service.CustomerService;
import com.xalt.kata.service.OperationService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OperationTest {

    @Autowired
    CustomerService customerService;

    @Autowired
    AccountService accountService;

    @Autowired
    OperationService operationService;

    @ParameterizedTest
    @ValueSource(longs = {100, 200, 300, 500, 1000})
    void should_withdrawal(long value) {

        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("Khalil");
        Customer createdCustomer = customerService.createCustomer(customer);
        assert(createdCustomer != null);

        Account account = new Account();
        account.setCustomer(createdCustomer);
        account.setBalance(1000);
        Account createdAccount = accountService.createAccount(account);
        assert(createdAccount != null);

        Operation operation = new Operation();
        operation.setAccount(createdAccount);
        operation.setType("withdrawal");
        operation.setAmount(value);

        Operation createdOperation = operationService.createOperation(operation);
        assert(createdOperation != null);

        operationService.executeOperation(operation);
        assert(createdAccount.getBalance() == 1000 - value);
    }

    @ParameterizedTest
    @ValueSource(longs = {100, 200, 300, 500, 1000})
    void should_deposit (long value) {

        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("Khalil");
        Customer createdCustomer = customerService.createCustomer(customer);
        assert(createdCustomer != null);

        Account account = new Account();
        account.setCustomer(createdCustomer);
        account.setBalance(0);
        Account createdAccount = accountService.createAccount(account);
        assert(createdAccount != null);

        Operation operation = new Operation();
        operation.setAccount(createdAccount);
        operation.setType("deposit");
        operation.setAmount(value);

        Operation createdOperation = operationService.createOperation(operation);
        assert(createdOperation != null);

        operationService.executeOperation(operation);
        assert(createdAccount.getBalance() == 0 + value);
    }

    @Test
    public void should_not_withdrawal() {

        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("Khalil");
        Customer createdCustomer = customerService.createCustomer(customer);
        assert(createdCustomer != null);

        Account account = new Account();
        account.setCustomer(createdCustomer);
        account.setBalance(0);
        Account createdAccount = accountService.createAccount(account);
        assert(createdAccount != null);

        Operation operation = new Operation();
        operation.setAccount(createdAccount);
        operation.setType("withdrawal");
        operation.setAmount(1);

        Operation createdOperation = operationService.createOperation(operation);
        assert(createdOperation != null);

        Exception exception = Assertions.assertThrows(InsufficientBalanceException.class, () -> {
            operationService.executeOperation(operation);
        });

        String expectedMessage = "Insufficient balance to execute the operation with id : ";
        String actualMessage = exception.getMessage();

        assert(createdAccount.getBalance() == 0);
        assert(actualMessage.contains(expectedMessage));
    }
}
