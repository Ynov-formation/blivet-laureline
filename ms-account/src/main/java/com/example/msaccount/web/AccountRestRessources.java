package com.example.msaccount.web;

import com.example.msaccount.dao.AccountRepository;
import com.example.msaccount.entities.Account;
import com.example.msaccount.service.AccountService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account/v1")
@Slf4j
public class AccountRestRessources {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;  

    public AccountRestRessources(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/accounts")
    public List<Account> listeAccounts() {
        log.info("Recuperation de la liste des comptes");
        List<Account> accounts = accountRepository.findAll();
        accounts.forEach(account -> {
            account.setClient(accountService.getClient(account.getId()));
        });
        return accounts;
    }

    @GetMapping("/accounts/{id}")
    public Account getAccount(@PathVariable(name="id") Long id) {
        log.info("Recuperation du compte avec l'id : {}", id);
        Account account = accountRepository.findById(id).get();
        account.setClient(accountService.getClient(id));
        return account;
    }

    @PostMapping("/accounts")
    public Account saveAccount(@RequestBody Account account) {
        log.info("Ajout d'un nouveau compte");
        return accountRepository.save(account);
    }

    @PutMapping("/accounts/{id}")
    public Account updateAccount(@PathVariable(name="id") Long id, @RequestBody Account account) {
        log.info("Mise a jour du compte avec l'id : {}", id);
        Account existingAccount = accountRepository.findById(id).orElse(null);
        if (existingAccount != null) {
            existingAccount.setNom(account.getNom());
            existingAccount.setDescription(account.getDescription());
            existingAccount.setMontant(account.getMontant());
            existingAccount.setClientId(account.getClientId());
            existingAccount.setClient(account.getClient());
            return accountRepository.save(existingAccount);
        } else {
            throw new RuntimeException("Account not found with id: " + id);
        }
    }

    @DeleteMapping("/accounts/{id}")
    public void deleteAccount(@PathVariable(name="id") Long id) {
        log.info("Suppression du compte avec l'id : {}", id);
        accountRepository.deleteById(id);
    }    

    @DeleteMapping("/accounts")
    public void deleteAllAccounts() {
        log.info("Suppression de tous les s");
        accountRepository.deleteAll();
    }
    
}

    

