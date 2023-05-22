package stu.najah.se.ui.control;

import stu.najah.se.core.service.aya.Logger;

import java.util.Scanner;

public class LogoutController {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);


        boolean loggedIn = false;

        while (true) {

            if (loggedIn) {
                Logger.print("You are logged in.");
                Logger.print("Press 'l' to log out, or any other key to continue.");
            } else {
                Logger.print("You are logged out.");
                Logger.print("Press 'l' to log in, or any other key to continue.");
            }


            String input = scanner.nextLine();


            if (input.equals("l")) {
                loggedIn = !loggedIn; // toggle logged in status
            } else {
                Logger.print("Continuing...");
            }


            if (!loggedIn) {
                Logger.print("You are now logged out.");
                break;
            }
        }


        scanner.close();
    }
}
