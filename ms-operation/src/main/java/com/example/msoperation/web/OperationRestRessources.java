package com.example.msoperation.web;

import com.example.msoperation.dao.OperationRepository;
import com.example.msoperation.service.OperationService;
import com.example.msoperation.entities.Account;
import com.example.msoperation.entities.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RestController
@RequestMapping("/operation/v1")
@Slf4j
public class OperationRestRessources {

    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private OperationService operationService;

    @GetMapping("/operations")
    public List<Operation> listOperationons() {
        log.info("Recuperation de la liste des operations");
        List<Operation> operations = operationRepository.findAll();
        operations.forEach(operation -> {
            operation.setAccountDestination(operationService.getAccountDestination(operation.getId()));
            operation.setAccountSource(operationService.getAccountSource(operation.getId()));
        });
        return operations;
    }

    @GetMapping("/operations/{id}")
    public Operation geOperationon(@PathVariable(name="id") Long id) {
        log.info("Recuperation de l'operation avec l'id : {}", id);
        Operation operation = operationRepository.findById(id).get();
        operation.setAccountDestination(operationService.getAccountDestination(operation.getId()));
        operation.setAccountSource(operationService.getAccountSource(operation.getId()));
        return operation;
    }

    @PostMapping("/operations")
    public Operation createOperation(@RequestBody Operation operation) {
        log.info("Ajout d'une nouvelle operation");
        return operationRepository.save(operation);
    }

    @PutMapping("/operations/{id}")
    public Operation updateOperation(@PathVariable(name="id") Long id, @RequestBody Operation operation) {
        log.info("Mise a jour de l'operation avec l'id : {}", id);
        Operation existingOperation = operationRepository.findById(id).get();
        existingOperation.setType(operation.getType());
        existingOperation.setAmount(operation.getAmount());
        existingOperation.setDate(operation.getDate());
        existingOperation.setIdAccountSource(operation.getIdAccountSource());
        existingOperation.setIdAccountDestination(operation.getIdAccountDestination());        
        return operationRepository.save(existingOperation);
    }

    @DeleteMapping("/operations/{id}")
    public void deleteOperation(@PathVariable(name="id") Long id) {
        log.info("Suppression de l'operation avec l'id : {}", id);
        operationRepository.deleteById(id);
    }

    @DeleteMapping("/operations")
    public void deleteAllOperations() {
        log.info("Suppression de toutes les operations");
        operationRepository.deleteAll();
    }


}
