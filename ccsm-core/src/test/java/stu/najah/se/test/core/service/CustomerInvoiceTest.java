package stu.najah.se.test.core.service;

import stu.najah.se.core.service.CustomerInvoice;

import java.util.ArrayList;

public class CustomerInvoiceTest {

    public static void main(String[] args) {
        ArrayList<String> itemsToClean = new ArrayList<String>();
        itemsToClean.add("Carpet");
        itemsToClean.add("Sofa");
        itemsToClean.add("Drapes");

        CustomerInvoice invoice = new CustomerInvoice("Sara Smith", "123 Main Street, Anytown, USA", "456 Elm Street, Anytown, USA", itemsToClean, 150.00);

        invoice.printInvoice();
    }
}
