package stu.najah.se;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

    /**
     * To be tested
     */
    Main main = new Main();

    @Test
    void isPrime() {
        // not primes
        assertFalse(main.isPrime(-5));
        assertFalse(main.isPrime(0));
        assertFalse(main.isPrime(1));
        assertFalse(main.isPrime(4));
        assertFalse(main.isPrime(100));
        assertFalse(main.isPrime(22222222));
        // primes
        assertTrue(main.isPrime(2));
        assertTrue(main.isPrime(7));
        assertTrue(main.isPrime(13));
        assertTrue(main.isPrime(101));
    }
}
