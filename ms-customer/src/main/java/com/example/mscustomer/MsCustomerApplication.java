package com.example.mscustomer;

import com.example.msaccount.entities.Account;
import com.example.mscustomer.dao.ClientRepository;
import com.example.mscustomer.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class MsCustomerApplication implements CommandLineRunner {
    @Autowired
    private ClientRepository clientRepository;

    public static void main(String[] args) {
        SpringApplication.run(MsCustomerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
            clientRepository.deleteAll();
            clientRepository.saveAll(List.of(
                Client.builder()
                        .nom("Blivet ")
                        .prenom("Laureline")
                        .email("laureline.blivet@ynoc.com")
                        .dateNaissance(LocalDate.of(2001, 3, 8))
                        .build(),
                Client.builder()
                        .nom("Blivet")
                        .prenom("Léa")
                        .email("lea@iclood.com")
                        .dateNaissance(LocalDate.of(2000, 1, 11))
                        .build()
        ));
    }
}