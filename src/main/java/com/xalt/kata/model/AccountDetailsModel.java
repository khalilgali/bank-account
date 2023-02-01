package com.xalt.kata.model;

import java.util.Date;
import java.util.List;

public class AccountDetailsModel {

    private long id;
    private long balance;
    private List<OperationModel> operationList;
    private Date createdAt;
    private Date lastModifiedAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(Date lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    public List<OperationModel> getOperationList() {
        return operationList;
    }

    public void setOperationList(List<OperationModel> operationList) {
        this.operationList = operationList;
    }
}
