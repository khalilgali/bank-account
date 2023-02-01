package com.xalt.kata.model;


import java.util.Date;
import java.util.List;

public class CustomerAccountDetailsModel {

    private long id;
    private String name;
    private Date birthDate;
    private String address;
    private Date createdAt;
    private Date lastModifiedAt;
    private List<AccountDetailsModel> accountDetailsModelList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public List<AccountDetailsModel> getAccountDetailsModelList() {
        return accountDetailsModelList;
    }

    public void setAccountDetailsModelList(List<AccountDetailsModel> accountDetailsModelList) {
        this.accountDetailsModelList = accountDetailsModelList;
    }
}
