package com.xalt.kata.service;

import com.xalt.kata.entity.Account;
import com.xalt.kata.entity.Operation;

import java.util.List;

public interface OperationService {

    Operation createOperation(Operation operation);
    List<Operation> getAllOperation();
    Operation getOperationById(long operationId);
    void executeOperation(Operation operation);
}
