package com.example.msaccount;

import com.example.msaccount.dao.AccountRepository;
import com.example.msaccount.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class MsAccountApplication implements CommandLineRunner {

    @Autowired
    private AccountRepository accountRepository;
    public static void main(String[] args) {
        SpringApplication.run(MsAccountApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        accountRepository.deleteAll();

        accountRepository.saveAll(List.of(
                Account.builder()
                        .nom("Compte courant")
                        .description("Description du compte courant")
                        .montant(500.0)
                        .clientId((long) 4)
                        .build(),
                Account.builder()
                        .nom("Compte epargne")
                        .description("Description du compte epargne")
                        .montant(2500.0)
                        .clientId((long) 5)
                        .build(),
                Account.builder()
                        .nom("Compte joint")
                        .description("Description du compte joint")
                        .montant(364.25)
                        .clientId((long) 52)
                        .build()
        ));
    }
}
