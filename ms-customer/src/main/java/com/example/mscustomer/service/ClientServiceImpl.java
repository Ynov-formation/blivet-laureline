package com.example.mscustomer.service;

import com.example.mscustomer.dao.ClientRepository;
import com.example.msaccount.entities.Account;
import com.example.msaccount.service.AccountService;
import com.example.mscustomer.entities.Client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService{
    
    public static final String ACCOUNTS = "/accounts/";
	private static final String ACCOUNT_SERVICE_URL = "http://localhost:8082/account/v1";
    private final RestTemplate restTemplate;            
    private ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository, AccountService accountService) {
        this.clientRepository = clientRepository;
		this.restTemplate = new RestTemplate();
    }

    @Override
    public List<Account> getAccounts(Long id) {        
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            ResponseEntity<List<Account>> response = restTemplate.exchange(
					ACCOUNT_SERVICE_URL + ACCOUNTS,
					HttpMethod.GET,
					null,
					new ParameterizedTypeReference<>() {
					}
			);
			List<Account> accounts = response.getBody();
            List<Account> accountsToReturn = new ArrayList<>();
            for (Account account : accounts) {
                if (account.getClient().getId() == id) {
                    accountsToReturn.add(account);
                }
            }
            return accountsToReturn;
        } else {
            return null;
        }
    }

}
