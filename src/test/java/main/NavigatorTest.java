package main;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import stu.najah.se.Navigator;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

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
