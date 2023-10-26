package com.ynov.commerce.sample.principle.vat;

public class InvoiceProcess {
    private VatService vatService;

    public InvoiceProcess(VatService vatService) {
        this.vatService = vatService;
    }

    void generateInvoice() {
        // ...
        int vat = vatService.getVat("FR");
        // ...
    }
}
