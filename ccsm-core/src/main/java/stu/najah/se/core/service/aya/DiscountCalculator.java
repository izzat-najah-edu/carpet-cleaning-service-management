package stu.najah.se.core.service.aya;

import java.util.Scanner;


public class DiscountCalculator {

    public static void main(String[] args) {


        final double DISCOUNT_RATE = 0.1; // 10% discount
        final double THRESHOLD_AMOUNT = 400; // 400 NIS


        Scanner scanner = new Scanner(System.in);


        Logger.print("Enter total amount spent: ");
        double totalAmount = scanner.nextDouble();


        if (totalAmount > THRESHOLD_AMOUNT) {
            double discountAmount = totalAmount * DISCOUNT_RATE;
            double discountedPrice = totalAmount - discountAmount;
            Logger.print("Congratulations, you qualify for a %.0f%% discount!%n", DISCOUNT_RATE * 100);
            Logger.print("Discount amount: %.2f NIS%n", discountAmount);
            Logger.print("Discounted price: %.2f NIS%n", discountedPrice);

        } else {
            Logger.print("Sorry, you do not qualify for a discount.");
        }


        scanner.close();
    }
}
