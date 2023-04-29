package stu.najah.se.core;

/**
 * <p>
 * The EmailConfirmationListener interface defines the contract for handling email confirmations.
 * </p><p>
 * Implementations of this interface should handle email confirmations according to the specific
 * requirements of the application, such as logging or displaying confirmation messages to the user.
 * </p>
 */
public interface EmailConfirmationListener {

    /**
     * The default confirmation message.
     */
    String DEFAULT_MESSAGE = "An email will be sent to the customer. Are you sure ?";

    /**
     * This method is called to handle email confirmations. It returns a boolean value indicating
     * the success of the confirmation. The given message can be used for reporting or logging purposes.
     *
     * @param message The confirmation message associated with the email.
     * @return true if the email confirmation was successful, false otherwise.
     */
    boolean onEmailConfirmation(String message);
}
