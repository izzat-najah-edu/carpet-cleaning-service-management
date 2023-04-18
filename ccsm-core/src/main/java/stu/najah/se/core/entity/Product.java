package stu.najah.se.core.entity;

import java.util.Scanner;

public class Product {
    private String name;
    private String picture;
    private String description;
    private boolean specialTreatment;

    public Product(String name, String picture, String description, boolean specialTreatment) {
        this.name = name;
        this.picture = picture;
        this.description = description;
        this.specialTreatment = specialTreatment;
    }

    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }

    public String getDescription() {
        return description;
    }

    public boolean requiresSpecialTreatment() {
        return specialTreatment;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the product name: ");
        String name = scanner.nextLine();

        System.out.print("Enter the picture file name: ");
        String picture = scanner.nextLine();

        System.out.print("Enter the product description (dimensions, material, etc.): ");
        String description = scanner.nextLine();

        System.out.print("Does the product require special treatment? (true/false): ");
        boolean specialTreatment = scanner.nextBoolean();

        Product product = new Product(name, picture, description, specialTreatment);
        System.out.println("Product Information: ");
        System.out.println("Name: " + product.getName());
        System.out.println("Picture file name: " + product.getPicture());
        System.out.println("Description: " + product.getDescription());
        System.out.println("Special Treatment Required: " + product.requiresSpecialTreatment());
    }
}
