package com.xalt.kata.controller;

import com.xalt.kata.entity.Account;
import com.xalt.kata.entity.Operation;
import com.xalt.kata.mapper.AccountMapper;
import com.xalt.kata.mapper.OperationMapper;
import com.xalt.kata.model.AccountModel;
import com.xalt.kata.model.OperationModel;
import com.xalt.kata.service.AccountService;
import com.xalt.kata.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private OperationService operationService;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private OperationMapper operationMapper;

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountModel>> getAllAccount()
    {
       List<AccountModel> accountModelList = accountService.getAllAccount()
                .stream()
                .map(account -> accountMapper.accountToAccountModel(account))
                .collect(Collectors.toList());
        return  ResponseEntity.ok().body(accountModelList);
    }

    @PostMapping("/accounts/{account-id}/operations")
    @Transactional
    public ResponseEntity<OperationModel> createAccountOperation(@PathVariable("account-id") long accountId , @RequestBody OperationModel operationModel)
    {
        Account account = accountService.getAccountById(accountId);
        Operation operation = operationMapper.operationModelToOperation(operationModel);
        operation.setAccount(account);
        OperationModel createdOperation = operationMapper.operationToOperationModel(operationService.createOperation(operation));
        operationService.executeOperation(operation);
        return ResponseEntity.ok().body(createdOperation);
    }

    @PutMapping("/accounts")
    public ResponseEntity<AccountModel> updateAccount(@RequestBody AccountModel accountModel)
    {
        Account account = accountMapper.accountModelToAccount(accountModel);
        AccountModel resultAccountModel = accountMapper.accountToAccountModel(accountService.updateAccount(account));
        return ResponseEntity.ok().body(resultAccountModel);
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<AccountModel> getAccountById(@PathVariable long id)
    {
        AccountModel resultAccountModel = accountMapper.accountToAccountModel(accountService.getAccountById(id));
        return ResponseEntity.ok().body(resultAccountModel);
    }

    @DeleteMapping("/accounts/{id}")
    public HttpStatus deleteAccountById(@PathVariable long id)
    {
        accountService.deleteAccountById(id);
        return HttpStatus.OK;
    }
}
