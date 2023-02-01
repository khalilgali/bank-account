package com.xalt.kata.service;

import com.xalt.kata.constant.OperationType;
import com.xalt.kata.entity.Account;
import com.xalt.kata.entity.Operation;
import com.xalt.kata.exception.InsufficientBalanceException;
import com.xalt.kata.exception.ResourceNotFoundException;
import com.xalt.kata.repository.AccountRepository;
import com.xalt.kata.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OperationServiceImpl implements OperationService{

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Operation createOperation(Operation operation) {
        return operationRepository.save(operation);
    }

    @Override
    public List<Operation> getAllOperation() {
        return operationRepository.findAll();
    }

    @Override
    public Operation getOperationById(long operationId) {

        Optional<Operation> operation = operationRepository.findById(operationId);

        if(operation.isPresent())
        {
            return operation.get();
        }
        else{
            throw new ResourceNotFoundException("Resource not found with id : "+ operationId);
        }
    }

    @Override
    public void executeOperation(Operation operation) {

        String operationType = operation.getType();

        // Withdrawal operation
        if(operationType.toUpperCase().equals(OperationType.WITHDRAWAL))
        {
            withdrawal(operation);
        }

        // Deposit operation
        else if(operationType.toUpperCase().equals(OperationType.DEPOSIT))
        {
            deposit(operation);
        }
        else
        {
            throw new TypeNotPresentException("The operation type is not correct !", null);
        }
    }

    public void withdrawal(Operation operation)
    {
        Account accountToUpdate = operation.getAccount();
        double operationAmount = operation.getAmount();

        if(accountToUpdate.getBalance() < operationAmount)
        {
            throw new InsufficientBalanceException("Insufficient balance to execute the operation with id : "+ operation.getId());
        }
        else
        {
            accountToUpdate.setBalance(accountToUpdate.getBalance() - operationAmount);
            accountRepository.save(accountToUpdate);
        }
    }

    public void deposit(Operation operation)
    {
        Account accountToUpdate = operation.getAccount();
        double operationAmount = operation.getAmount();

        accountToUpdate.setBalance(accountToUpdate.getBalance() + operationAmount);
        accountRepository.save(accountToUpdate);
    }
}
