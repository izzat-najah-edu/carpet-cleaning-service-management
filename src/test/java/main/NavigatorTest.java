package main;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import stu.najah.se.Navigator;

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
