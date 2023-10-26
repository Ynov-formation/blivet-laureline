package com.ynov.commerce.sample.principle.impl;

import com.ynov.commerce.sample.principle.service.IRHService;

public class RHService implements IRHService {

    @Override
    public void paySalary() {
        System.out.println("Pay salary");
    }
}
