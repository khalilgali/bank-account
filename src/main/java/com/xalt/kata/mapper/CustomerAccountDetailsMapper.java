package com.xalt.kata.mapper;

import com.xalt.kata.entity.Customer;
import com.xalt.kata.model.CustomerAccountDetailsModel;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , injectionStrategy = InjectionStrategy.FIELD)
public interface CustomerAccountDetailsMapper {

    @Mapping(target="id", source="customer.id")
    @Mapping(target="name", source="customer.name")
    @Mapping(target="birthDate", source="customer.birthDate")
    @Mapping(target="address", source="customer.address")
    @Mapping(target="accountDetailsModelList", source="customer.accountList")
    @Mapping(target="createdAt", source="customer.createdAt")
    @Mapping(target="lastModifiedAt", source="customer.lastModifiedAt")
    CustomerAccountDetailsModel customerToCustomerAccountDetailsModel(Customer customer);
}
