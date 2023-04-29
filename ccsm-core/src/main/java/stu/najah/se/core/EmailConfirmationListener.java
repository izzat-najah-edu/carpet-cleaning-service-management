package stu.najah.se.core;

/**
 * <p>
 * The EmailConfirmationListener interface defines the contract for handling email confirmations
 * and sent email notifications.
 * </p><p>
 * Implementations of this interface should handle email confirmations and sent email notifications
 * according to the specific requirements of the application, such as logging or displaying
 * confirmation messages and sent email notifications to the user.
 * </p>
 */
public interface EmailConfirmationListener {

    /**
     * The default confirmation message.
     */
    String DEFAULT_CONFIRMATION_MESSAGE = "An email will be sent to the customer. Are you sure ?";

    /**
     * The default success message.
     */
    String DEFAULT_SUCCESS_MESSAGE = "An email has been sent successfully!";

    /**
     * This method is called to handle email confirmations. It returns a boolean value indicating
     * the success of the confirmation. The given message can be used for reporting or logging purposes.
     *
     * @param message The confirmation message associated with the email.
     * @return true if the email confirmation was successful, false otherwise.
     */
    boolean onEmailConfirmation(String message);

    /**
     * This method is called when an email has been sent successfully. It handles the sent email
     * notification with the given message, which can be used for reporting or logging purposes.
     *
     * @param message The message associated with the sent email notification.
     */
    void onEmailSent(String message);
}
