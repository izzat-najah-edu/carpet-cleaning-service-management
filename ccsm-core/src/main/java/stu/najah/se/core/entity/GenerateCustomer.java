package stu.najah.se.core.entity;

import java.util.ArrayList;
import java.util.Scanner;

public class GenerateCustomer {
    private String name;
    private String address;

    public GenerateCustomer(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}

class Order {
    private Customer customer;
    private ArrayList<String> items;
    private boolean delivery;
    private double price;

    public Order(Customer customer, ArrayList<String> items, boolean delivery, double price) {
        this.customer = customer;
        this.items = items;
        this.delivery = delivery;
        this.price = price;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ArrayList<String> getItems() {
        return items;
    }

    public boolean requiresDelivery() {
        return delivery;
    }

    public double getPrice() {
        return price;
    }
}

public class InvoiceGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.print("Enter the customer name: ");
        String name = scanner.nextLine();

        System.out.print("Enter the customer address: ");
        String address = scanner.nextLine();

        GenerateCustomer generatecustomer = new GenerateCustomer(name, address);


        ArrayList<String> items = new ArrayList<>();
        System.out.print("Enter the items to be cleaned: ");
        String item = scanner.nextLine();
        while (!item.equals("done")) {
            items.add(item);
            item = scanner.nextLine();
        }

        System.out.print("Does the customer require delivery? (true/false): ");
        boolean delivery = scanner.nextBoolean();

        System.out.print("Enter total price: ");
        double price = scanner.nextDouble();

        Order order = new Order(customer, items, delivery, price);


        System.out.println("Invoice for " + order.getCustomer().getName() + ":");
        System.out.println("Address: " + order.getCustomer().getAddress());
        System.out.println("Items to be cleaned: ");
        for (String item : order.getItems()) {
            System.out.println("- " + item);
        }
        System.out.println("Price: $" + order.getPrice());
        if (order.requiresDelivery()) {
            System.out.println("Delivery fee: $10");
            System.out.println("Total: $" + (order.getPrice() + 10));
        } else {
            System.out.println("Total: $" + order.getPrice());
        }
    }
}
