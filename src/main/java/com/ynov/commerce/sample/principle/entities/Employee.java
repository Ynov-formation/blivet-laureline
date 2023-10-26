package com.ynov.commerce.sample.principle.entities;


import com.ynov.commerce.sample.principle.service.IDBService;
import com.ynov.commerce.sample.principle.service.IJSONService;
import com.ynov.commerce.sample.principle.service.IRHService;
import com.ynov.commerce.sample.principle.service.IXMLService;

public class Employee  {

    private IDBService idbService;
    private IXMLService ixmlService;
    private IJSONService ijsonService;
    private IRHService irhService;

    public Employee(IDBService idbService, IXMLService ixmlService, IJSONService ijsonService, IRHService irhService) {
        this.idbService = idbService;
        this.ixmlService = ixmlService;
        this.ijsonService = ijsonService;
        this.irhService = irhService;
    }


    public void saveToDatabase() {
        this.idbService.saveToDatabase();
    }

    public void saveXML() {
        this.ixmlService.saveXML();
    }

    public void saveJSON() {
        this.ijsonService.saveJSON();
    }


    public void paySalary() {
        this.irhService.paySalary();
    }

}
