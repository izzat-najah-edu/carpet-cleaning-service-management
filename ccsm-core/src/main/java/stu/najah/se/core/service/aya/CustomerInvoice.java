package stu.najah.se.core.service.aya;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerInvoice {

    private String customerName;
    private String customerAddress;
    private String deliveryAddress;
    private double totalPrice;
    private ArrayList<String> itemsToClean;
    final String LINE_SEPARATOR = "=================================";

    public CustomerInvoice(String customerName, String customerAddress, String deliveryAddress, ArrayList<String> itemsToClean, double totalPrice) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.deliveryAddress = deliveryAddress;
        this.itemsToClean = itemsToClean;
        this.totalPrice = totalPrice;
    }

    public void printInvoice() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("CUSTOMER INFORMATION");
        System.out.println(LINE_SEPARATOR);
        System.out.println("Customer Name: " + customerName);
        System.out.println("Customer Address: " + customerAddress);
        System.out.println("Delivery Address: " + deliveryAddress);
        System.out.println(LINE_SEPARATOR);
        System.out.println("ITEMS TO CLEAN");
        System.out.println(LINE_SEPARATOR);
        for (String item : itemsToClean) {
            System.out.println("- " + item);
        }
        System.out.println(LINE_SEPARATOR);
        System.out.println("TOTAL PRICE");
        System.out.println(LINE_SEPARATOR);
        System.out.println("$" + totalPrice);
        System.out.println(LINE_SEPARATOR);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter customer name:");
        String customerName = sc.nextLine();

        System.out.println("Enter customer address:");
        String customerAddress = sc.nextLine();

        System.out.println("Enter delivery address:");
        String deliveryAddress = sc.nextLine();

        ArrayList<String> itemsToClean = new ArrayList<String>();
        while (true) {
            System.out.println("Enter item to clean (or type 'done' to finish):");
            String item = sc.nextLine();
            if (item.equals("done")) {
                break;
            }
            itemsToClean.add(item);
        }

        System.out.println("Enter total price:");
        double totalPrice = sc.nextDouble();

        CustomerInvoice invoice = new CustomerInvoice(customerName, customerAddress, deliveryAddress, itemsToClean, totalPrice);
        invoice.printInvoice();
    }
}
