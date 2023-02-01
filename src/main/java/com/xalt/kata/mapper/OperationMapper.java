package com.xalt.kata.mapper;

import com.xalt.kata.entity.Operation;
import com.xalt.kata.model.OperationModel;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , injectionStrategy = InjectionStrategy.FIELD)
public interface OperationMapper {
    @Mapping(target="id", source="operation.id")
    @Mapping(target="amount", source="operation.amount")
    @Mapping(target="type", source="operation.type")
    @Mapping(target="createdAt", source="operation.createdAt")
    @Mapping(target="lastModifiedAt", source="operation.lastModifiedAt")
    OperationModel operationToOperationModel(Operation operation);

    @Mapping(target="id", source="operationModel.id")
    @Mapping(target="amount", source="operationModel.amount")
    @Mapping(target="type", source="operationModel.type")
    @Mapping(target="createdAt", source="operationModel.createdAt")
    @Mapping(target="lastModifiedAt", source="operationModel.lastModifiedAt")
    Operation operationModelToOperation(OperationModel operationModel);
}
