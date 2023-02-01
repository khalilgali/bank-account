package com.xalt.kata.controller;

import com.xalt.kata.mapper.OperationMapper;
import com.xalt.kata.model.OperationModel;
import com.xalt.kata.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OperationController {

    @Autowired
    private OperationService operationService;

    @Autowired
    private OperationMapper operationMapper;

    @GetMapping("/operations")
    public ResponseEntity<List<OperationModel>> getAllOperation()
    {
        List<OperationModel> operationModelList = operationService.getAllOperation()
                .stream()
                .map(operation -> operationMapper.operationToOperationModel(operation))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(operationModelList);
    }

    @GetMapping("/operations/{id}")
    public ResponseEntity<OperationModel> getOperationById(@PathVariable long id)
    {
        OperationModel operationModel = operationMapper.operationToOperationModel(operationService.getOperationById(id));
        return ResponseEntity.ok().body(operationModel);
    }
}
