package stu.najah.se.sql;

import stu.najah.se.Navigator;

import java.sql.SQLException;
import java.util.logging.Logger;

public class Admin {

    private final Logger logger = Logger.getLogger(Admin.class.getName());
    private boolean logState = false;

    public void setLogState(boolean b) {
        logState = b;
    }

    public boolean login(String username, String password) {
        if (logState) {
            logger.info("You are already logged in");
            return false;
        }
        try {
            String pass = getPassword(username);
            // user found
            if (pass.equals(password)) {
                logger.info("You're successfully logged in");
                logState = true;
                return true;
            } else {
                logger.info("The password is wrong");
                return false;
            }
        } catch (SQLException e) {
            // user not found
            logger.info(e.getMessage());
            return false;
        }
    }

    public boolean getLogState() {
        return logState;
    }

    public void logout() {
        logState = false;
    }

    /**
     * Connects to the database and retrieves the password of the given user
     * @param username name of the admin
     * @return password stored in the database
     * @throws SQLException if the username is not found
     */
    public static String getPassword(String username) throws SQLException {
        var connection = Navigator.getConnection();
        var statement = connection.createStatement();
        var resultSet = statement.executeQuery(
                "select password from admin where username = " + username
        );
        if (!resultSet.next()) {
            throw new SQLException("no admin by this username");
        }
        return resultSet.getString("password");
    }

}
