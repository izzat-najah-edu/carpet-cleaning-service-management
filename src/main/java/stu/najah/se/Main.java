package stu.najah.se;

public class Main {

    /**
     * checks if x is a prime number or not.
     * the test is done by dividing x to all numbers from 2 up to sqrt(x),
     * if at least one number divides x then x is not a prime,
     * Note that if x is 0, 1, or negative it's not a prime.
     * @param x to be tested
     * @return whether x is prime or not
     */
    boolean isPrime(int x) {
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
