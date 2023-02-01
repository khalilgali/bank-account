package com.xalt.kata.service;

import com.xalt.kata.entity.Account;

import java.util.List;

public interface AccountService {
    Account createAccount(Account account);
    Account updateAccount(Account account);
    List<Account> getAllAccount();
    Account getAccountById(long accountId);
    void deleteAccountById(long accountId);
}
