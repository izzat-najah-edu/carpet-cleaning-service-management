package stu.najah.se.test.ui.features.aya;


import java.util.Scanner;


public class Discount {

    public static void main(String[] args) {


        final double DISCOUNT_RATE = 0.1; // 10% discount
        final double THRESHOLD_AMOUNT = 400; // 400 NIS


        Scanner scanner = new Scanner(System.in);


        System.out.print("Enter total amount spent: ");
        double totalAmount = scanner.nextDouble();


        if (totalAmount > THRESHOLD_AMOUNT) {
            double discountAmount = totalAmount * DISCOUNT_RATE;
            double discountedPrice = totalAmount - discountAmount;
            System.out.printf("Congratulations, you qualify for a %.0f%% discount!\n", DISCOUNT_RATE * 100);
            System.out.printf("Discount amount: %.2f NIS\n", discountAmount);
            System.out.printf("Discounted price: %.2f NIS\n", discountedPrice);
        } else {
            System.out.println("Sorry, you do not qualify for a discount.");
        }


        scanner.close();
    }
}

