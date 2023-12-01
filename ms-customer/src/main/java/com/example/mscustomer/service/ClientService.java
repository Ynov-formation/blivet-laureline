package com.example.mscustomer.service;

import com.example.msaccount.entities.Account;
import java.util.List;

public interface ClientService {    
    List<Account> getAccounts(Long id);
}
