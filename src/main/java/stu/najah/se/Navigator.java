package stu.najah.se;

import org.jetbrains.annotations.NotNull;

import java.sql.Connection;

/**
 * This is the main class where the application starts,
 * it includes a static method to get the database connection
 */
public class Navigator {

    /**
     * @return the database connection
     */
    @NotNull
    public static Connection getConnection() {
        return null;
    }

    /**
     * TEST FUNCTION.
     * checks if x is a prime number or not.
     * the test is done by dividing x to all numbers from 2 up to sqrt(x),
     * if at least one number divides x then x is not a prime,
     * Note that if x is 0, 1, or negative it's not a prime.
     * @param x to be tested
     * @return whether x is prime or not
     */
    static boolean isPrime(int x) {
        if(x < 2) {
            return false;
        }
        int sqrt = (int) Math.sqrt(x);
        for (int i = 2; i <= sqrt; i++) {
            if(x % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}
