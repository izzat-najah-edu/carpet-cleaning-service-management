package stu.najah.se.sql;

import stu.najah.se.sql.entity.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * A database-connection wrapper class.
 * with different methods to perform SQL query on the database.
 */
public class QueryManager {

    private final PreparedStatement selectExistsAdmin;

    /**
     * Creates an object and initializes all prepared statements with the connection
     * @param connection to be used in construction of prepared statements
     */
    public QueryManager(Connection connection) throws SQLException {
        selectExistsAdmin = connection.prepareStatement(
                "select exists(select * from admin where username=? and password=?)"
        );
    }

    /**
     * Checks the given admin if it exists in the database or not.
     * @param admin to be checked
     * @return whether the admin exists or not
     */
    public boolean checkExists(Admin admin) {
        try {
            // no error is expected... so the exception is caught
            selectExistsAdmin.setString(1, admin.getUsername());
            selectExistsAdmin.setString(2, admin.getPassword());
            var set = selectExistsAdmin.executeQuery();
            set.next();
            // the result is always a single boolean value
            return set.getBoolean(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
