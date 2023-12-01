package com.example.msoperation.service;

import java.util.List;

import com.example.msoperation.entities.Account;
import com.example.msoperation.entities.Operation;

public interface OperationService {    
    Operation deposit(Long accountId, double amount);
	Operation withdraw(Long accountId, double amount);
	Operation transfer(Long sourceAccountId, Long destinationAccountId, double amount);
    List<Operation> getOperations(Long accountId);
    Account getAccountDestination(Long id);
    Account getAccountSource(Long id);
}
