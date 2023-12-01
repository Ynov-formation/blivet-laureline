package com.example.msaccount.service;

import com.example.msaccount.dao.AccountRepository;
import com.example.msaccount.entities.Account;
import com.example.msaccount.entities.Client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{
    
    public static final String CLIENTS = "/clients/";
	private static final String CLIENT_SERVICE_URL = "http://localhost:8082/customer/v1";
    private final RestTemplate restTemplate;            
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
		this.restTemplate = new RestTemplate();
    }

    @Override
    public Client getClient(Long id) {        
		Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) {
            ResponseEntity<Client> response = restTemplate.getForEntity(CLIENT_SERVICE_URL + CLIENTS + "/" + account.get().getId(), Client.class);
            return response.getBody();            
		}  else {
            return null;
        }
    }

}
