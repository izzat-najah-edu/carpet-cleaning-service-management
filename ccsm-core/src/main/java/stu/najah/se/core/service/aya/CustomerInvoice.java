package stu.najah.se.core.service.aya;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerInvoice {

    private String customerName;
    private String customerAddress;
    private String deliveryAddress;
    private double totalPrice;
    private ArrayList<String> itemsToClean;
    static final String LINE_SEPARATOR_CONSTANT = "=================================";


    public CustomerInvoice(String customerName,
                           String customerAddress,
                           String deliveryAddress,
                           List<String> itemsToClean,
                           double totalPrice) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.deliveryAddress = deliveryAddress;
        this.itemsToClean = new ArrayList<>(itemsToClean);
        this.totalPrice = totalPrice;
    }

    //..
    public void printInvoice() {
        Logger.print(LINE_SEPARATOR_CONSTANT);
        Logger.print("CUSTOMER INFORMATION");
        Logger.print(LINE_SEPARATOR_CONSTANT);
        Logger.print("Customer Name: " + customerName);
        Logger.print("Customer Address: " + customerAddress);
        Logger.print("Delivery Address: " + deliveryAddress);
        Logger.print(LINE_SEPARATOR_CONSTANT);
        Logger.print("ITEMS TO CLEAN");
        Logger.print(LINE_SEPARATOR_CONSTANT);
        for (String item : itemsToClean) {
            Logger.print("- " + item);
        }
        Logger.print(LINE_SEPARATOR_CONSTANT);
        Logger.print("TOTAL PRICE");
        Logger.print(LINE_SEPARATOR_CONSTANT);
        Logger.print("$" + totalPrice);
        Logger.print(LINE_SEPARATOR_CONSTANT);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Logger.print("Enter customer name:");
        String customerName = sc.nextLine();

        Logger.print("Enter customer address:");
        String customerAddress = sc.nextLine();

        Logger.print("Enter delivery address:");
        String deliveryAddress = sc.nextLine();

        ArrayList<String> itemsToClean = new ArrayList<>();
        while (true) {
            Logger.print("Enter item to clean (or type 'done' to finish):");
            String item = sc.nextLine();
            if (item.equals("done")) {
                break;
            }
            itemsToClean.add(item);
        }

        Logger.print("Enter total price:");
        double totalPrice = sc.nextDouble();

        CustomerInvoice invoice = new CustomerInvoice(customerName, customerAddress, deliveryAddress, itemsToClean, totalPrice);
        invoice.printInvoice();
    }
}
