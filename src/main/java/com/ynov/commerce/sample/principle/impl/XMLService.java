package com.ynov.commerce.sample.principle.impl;

import com.ynov.commerce.sample.principle.service.IXMLService;

public class XMLService implements IXMLService {

    @Override
    public void saveXML() {
        System.out.println("Save to XML");
    }
}
