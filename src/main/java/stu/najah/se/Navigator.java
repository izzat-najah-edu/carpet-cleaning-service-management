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
        // to be implemented ...
        return null;
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}
