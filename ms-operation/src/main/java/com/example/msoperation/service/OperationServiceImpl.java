package com.example.msoperation.service;


import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;

import com.example.msoperation.dao.OperationRepository;
import com.example.msoperation.entities.Account;
import com.example.msoperation.entities.Operation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OperationServiceImpl implements OperationService{
    
    public static final String ACCOUNTS = "/accounts/";
	private static final String ACCOUNT_SERVICE_URL = "http://localhost:8082/account/v1";
    private final RestTemplate restTemplate;            
    private OperationRepository operationRepository;

    public OperationServiceImpl(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
		this.restTemplate = new RestTemplate();
    }


    @Override
    public Operation deposit(Long accountId, double montant) {
        Account account = restTemplate.getForObject(ACCOUNT_SERVICE_URL + ACCOUNTS + accountId, Account.class);
		if (account != null) {
            account.setMontant(account.getMontant() + montant);
			restTemplate.put(ACCOUNT_SERVICE_URL + ACCOUNTS + accountId, account);
			return Operation.builder()
                    .type("Crédit")
                    .amount(montant)
                    .date(LocalDateTime.now())
                    .idAccountDestination(accountId)
                    .build();
		}
		return null;
    }

    @Override
    public Operation withdraw(Long accountId, double montant) {
        Account account = restTemplate.getForObject(ACCOUNT_SERVICE_URL + ACCOUNTS + accountId, Account.class);
		if (account != null) {
            account.setMontant(account.getMontant() - montant);
			restTemplate.put(ACCOUNT_SERVICE_URL + ACCOUNTS + accountId, account);
			return Operation.builder()
                    .type("Débit")
                    .amount(montant)
                    .date(LocalDateTime.now())
                    .idAccountDestination(accountId)
                    .build();
		}
		return null;
    }

    @Override
    public Operation transfer(Long sourceAccountId, Long destinationAccountId, double montant) {
        Account accountSource = restTemplate.getForObject(ACCOUNT_SERVICE_URL + ACCOUNTS + sourceAccountId, Account.class);        
        Account accountDestination = restTemplate.getForObject(ACCOUNT_SERVICE_URL + ACCOUNTS + destinationAccountId, Account.class);

		if (accountSource != null && accountDestination != null) {
            accountSource.setMontant(accountSource.getMontant() - montant);
			restTemplate.put(ACCOUNT_SERVICE_URL + ACCOUNTS + sourceAccountId, accountSource);
            accountDestination.setMontant(accountDestination.getMontant() + montant);
			restTemplate.put(ACCOUNT_SERVICE_URL + ACCOUNTS + destinationAccountId, accountDestination);
			return Operation.builder()
                    .type("Virement")
                    .amount(montant)
                    .date(LocalDateTime.now())
                    .idAccountSource(sourceAccountId)
                    .idAccountDestination(destinationAccountId)
                    .build();
		}
		return null;
    }

    @Override
    public List<Operation> getOperations(Long accountId) {             
        List<Operation> operations = operationRepository.findAll();
        List<Operation> operationsToReturn = new ArrayList<>();
        for (Operation operation : operations) {
            if (operation.getIdAccountSource() == accountId || operation.getIdAccountDestination() == accountId) {
                operationsToReturn.add(operation);
            }
        }
        return operationsToReturn;
    }


    @Override
    public Account getAccountDestination(Long id) {
        Optional<Operation> operation = operationRepository.findById(id);
        if (operation.isPresent()) {
            ResponseEntity<Account> response = restTemplate.getForEntity(ACCOUNT_SERVICE_URL + ACCOUNTS + "/" + operation.get().getIdAccountDestination(), Account.class);
            return response.getBody();            
		}  else {
            return null;
        }       
    }


    @Override
    public Account getAccountSource(Long id) {
         Optional<Operation> operation = operationRepository.findById(id);
        if (operation.isPresent()) {
            ResponseEntity<Account> response = restTemplate.getForEntity(ACCOUNT_SERVICE_URL + ACCOUNTS + "/" + operation.get().getIdAccountSource(), Account.class);
            return response.getBody();            
		}  else {
            return null;
        }  
    }
}
