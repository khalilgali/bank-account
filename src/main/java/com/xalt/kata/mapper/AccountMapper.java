package com.xalt.kata.mapper;

import com.xalt.kata.entity.Account;
import com.xalt.kata.model.AccountModel;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , injectionStrategy = InjectionStrategy.FIELD)
public interface AccountMapper {

        @Mapping(target="id", source="account.id")
        @Mapping(target="balance", source="account.balance")
        @Mapping(target="createdAt", source="account.createdAt")
        @Mapping(target="lastModifiedAt", source="account.lastModifiedAt")
        AccountModel accountToAccountModel(Account account);

        @Mapping(target="id", source="accountModel.id")
        @Mapping(target="balance", source="accountModel.balance")
        @Mapping(target="createdAt", source="accountModel.createdAt")
        @Mapping(target="lastModifiedAt", source="accountModel.lastModifiedAt")
        Account accountModelToAccount(AccountModel accountModel);

}
