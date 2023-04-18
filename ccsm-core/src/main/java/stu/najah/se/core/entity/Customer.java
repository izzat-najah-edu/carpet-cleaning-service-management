package stu.najah.se.core.entity;

import java.util.Scanner;

public class Customer {
    private String name;
    private String phone;
    private String address;

    public Customer(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the name: ");
        String name = scanner.nextLine();

        System.out.print("Enter the phone number: ");
        String phone = scanner.nextLine();

        System.out.print("Enter the address: ");
        String address = scanner.nextLine();

        Customer customer = new Customer(name, phone, address);
        System.out.println("Customer Info: ");
        System.out.println("Name: " + customer.getName());
        System.out.println("Phone: " + customer.getPhone());
        System.out.println("Address: " + customer.getAddress());
    }
}
