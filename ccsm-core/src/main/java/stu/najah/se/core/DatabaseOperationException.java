package stu.najah.se.core;

public class DatabaseOperationException extends RuntimeException {

    public DatabaseOperationException() {
    }

    public DatabaseOperationException(String message) {
        super(message);
    }

    public DatabaseOperationException(Throwable cause) {
        super(cause);
    }

    public DatabaseOperationException(String message, Throwable cause) {
        super(message, cause);
    }

}
