package com.xalt.kata.mapper;

import com.xalt.kata.entity.Customer;
import com.xalt.kata.model.CustomerModel;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , injectionStrategy = InjectionStrategy.FIELD)
public interface CustomerMapper {

    @Mapping(target="id", source="customer.id")
    @Mapping(target="name", source="customer.name")
    @Mapping(target="birthDate", source="customer.birthDate")
    @Mapping(target="address", source="customer.address")
    @Mapping(target="createdAt", source="customer.createdAt")
    @Mapping(target="lastModifiedAt", source="customer.lastModifiedAt")
    CustomerModel customerToCustomerModel(Customer customer);

    @Mapping(target="id", source="customerModel.id")
    @Mapping(target="name", source="customerModel.name")
    @Mapping(target="birthDate", source="customerModel.birthDate")
    @Mapping(target="address", source="customerModel.address")
    @Mapping(target="createdAt", source="customerModel.createdAt")
    @Mapping(target="lastModifiedAt", source="customerModel.lastModifiedAt")
    Customer customerModelToCustomer(CustomerModel customerModel);
}
