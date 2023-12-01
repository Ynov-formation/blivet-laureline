package com.example.mscustomer.web;

import com.example.msaccount.entities.Account;
import com.example.mscustomer.dao.ClientRepository;
import com.example.mscustomer.entities.Client;
import com.example.mscustomer.service.ClientService;

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
@RequestMapping("/customer/v1")
@Slf4j
public class CustomerRestRessources {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientService clientService;

    public CustomerRestRessources(ClientRepository clientRepository, ClientService clientService) {
        this.clientRepository = clientRepository;
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public List<Client> listeClients() {
        log.info("Recuperation de la liste des clients");
        return clientRepository.findAll();
    }
    @GetMapping("/clients/{id}")
    public Client getClient(@PathVariable(name="id") Long id) {
        log.info("Recuperation du client avec l'id : {}", id);
        return clientRepository.findById(id).get();
    }

    @GetMapping("/clients/{id}/accounts")
    public List<Account> getAccounts(@PathVariable(name="id") Long id) {
        log.info("Recuperation des comptes du client avec l'id : {}", id);
        return clientService.getAccounts(id);
    }

    @PostMapping("/clients")
    public Client saveClient(@RequestBody Client client) {
        log.info("Ajout d'un nouveau client");
        return clientRepository.save(client);
    }

    @PutMapping("/clients/{id}")
    public Client updateClient(@PathVariable(name="id") Long id, @RequestBody Client client) {
        log.info("Mise a jour du client avec l'id : {}", id);
        Client existingClient = clientRepository.findById(id).orElse(null);
        if (existingClient != null) {
            existingClient.setNom(client.getNom());
            existingClient.setPrenom(client.getPrenom());
            existingClient.setEmail(client.getEmail());
            existingClient.setDateNaissance(client.getDateNaissance());            
            return clientRepository.save(existingClient);
        } else {
            throw new RuntimeException("Client not found with id: " + id);
        }
    }

    @DeleteMapping("/clients/{id}")
    public void deleteClient(@PathVariable(name="id") Long id) {
        log.info("Suppression du client avec l'id : {}", id);
        clientRepository.deleteById(id);
    }    

    @DeleteMapping("/clients")
    public void deleteAllClients() {
        log.info("Suppression de tous les clients");
        clientRepository.deleteAll();
    }
}
