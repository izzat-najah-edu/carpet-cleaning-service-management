package main;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stu.najah.se.Navigator;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class NavigatorTest {

    @BeforeAll
    public static void start() {
        Navigator.main(new String[]{});
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * Tests the connection given by the Navigator class,
     * the connection shouldn't be null,
     * the connection shouldn't have auto commit on
     */
    @Test
    void getConnection() throws SQLException {
        Connection con = Navigator.getConnection();
        assertNotNull(con);
        assertFalse(con.getAutoCommit());
    }
}
