package com.ynov.commerce.sample.principle.impl;

import com.ynov.commerce.sample.principle.service.IJSONService;

public class JSONService implements IJSONService {

    @Override
    public void saveJSON() {
        System.out.println("Save to JSON");
    }
}
