package stu.najah.se.ui.control;

import java.util.Scanner;

public class LogoutController {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);


        boolean loggedIn = false;

        while (true) {

            if (loggedIn) {
                System.out.println("You are logged in.");
                System.out.println("Press 'l' to log out, or any other key to continue.");
            } else {
                System.out.println("You are logged out.");
                System.out.println("Press 'l' to log in, or any other key to continue.");
            }


            String input = scanner.nextLine();


            if (input.equals("l")) {
                loggedIn = !loggedIn; // toggle logged in status
            } else {
                System.out.println("Continuing...");
            }


            if (!loggedIn) {
                System.out.println("You are now logged out.");
                break;
            }
        }


        scanner.close();
    }
}

