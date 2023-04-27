package stu.najah.se.core;

public class EmailException extends RuntimeException {

    public EmailException() {
    }

    public EmailException(String message) {
        super(message);
    }

    public EmailException(Throwable cause) {
        super(cause);
    }

    public EmailException(String message, Throwable cause) {
        super(message, cause);
    }

}
