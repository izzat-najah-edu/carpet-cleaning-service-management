package stu.najah.se;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class NavigatorTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void isPrime() {
        // not primes
        assertFalse(Navigator.isPrime(-5));
        assertFalse(Navigator.isPrime(0));
        assertFalse(Navigator.isPrime(1));
        assertFalse(Navigator.isPrime(4));
        assertFalse(Navigator.isPrime(100));
        assertFalse(Navigator.isPrime(22222222));
        // primes
        assertTrue(Navigator.isPrime(2));
        assertTrue(Navigator.isPrime(7));
        assertTrue(Navigator.isPrime(13));
        assertTrue(Navigator.isPrime(101));
    }

    /**
     * Tests the connection given by the Navigator class,
     * the connection shouldn't be null,
     * the connection must have a valid schema,
     * the connection shouldn't have auto commit on
     */
    @Disabled("database connection is not implemented yet")
    @Test
    void getConnection() throws SQLException {
        Connection con = Navigator.getConnection();
        assertNotNull(con);
        assertNotNull(con.getSchema());
        assertFalse(con.getAutoCommit());
    }
}
