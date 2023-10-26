package com.ynov.commerce.sample.principle.impl;

import com.ynov.commerce.sample.principle.service.IDBService;

public class DBService implements IDBService {

   @Override
    public void saveToDatabase() {
        System.out.println("Save to database");
    }




}
