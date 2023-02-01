package com.xalt.kata.service;

import com.xalt.kata.entity.Account;
import com.xalt.kata.exception.ResourceNotFoundException;
import com.xalt.kata.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account updateAccount(Account account) {

        Optional<Account> accountToUpdate = accountRepository.findById(account.getId());

        if(accountToUpdate.isPresent())
        {
            Account accountUpdate = accountToUpdate.get();
            accountUpdate.setId(account.getId());
            accountUpdate.setCustomer(account.getCustomer());
            accountUpdate.setBalance(account.getBalance());
            accountRepository.save(accountUpdate);
            return accountUpdate;
        }
        else{
            throw new ResourceNotFoundException("Resource not found with id : "+ account.getId());
        }
    }

    @Override
    public List<Account> getAllAccount() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccountById(long accountId) {

        Optional<Account> account = accountRepository.findById(accountId);

        if(account.isPresent())
        {
            return account.get();
        }
        else{
            throw new ResourceNotFoundException("Resource not found with id : "+ accountId);
        }
    }

    @Override
    public void deleteAccountById(long accountId) {

        Optional<Account> account = accountRepository.findById(accountId);

        if(account.isPresent())
        {
            accountRepository.delete(account.get());
        }
        else{
            throw new ResourceNotFoundException("Resource not found with id : "+ accountId);
        }
    }
}

