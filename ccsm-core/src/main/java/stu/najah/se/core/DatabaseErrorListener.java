package stu.najah.se.core;

/**
 * <p>
 * The DatabaseErrorListener interface defines the contract for handling and reporting
 * database transaction errors.
 * </p><p>
 * Implementations of this interface should handle errors
 * according to the specific requirements of the application, such as logging or displaying
 * error messages to the user.
 * </p>
 */
public interface DatabaseErrorListener {

    /**
     * This method is called when a transaction error occurs. It handles the error with the
     * given error message, which can be used for reporting or logging purposes.
     *
     * @param message The error message associated with the transaction error.
     */
    void onTransactionError(String message);
}
