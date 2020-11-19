package ua.dnipro.epam.homework.exception;

public class DBException extends RuntimeException {

    public DBException(Throwable cause) {
        super(cause);
    }

    public DBException(String message, Throwable cause) {
        super(message, cause);
    }

    public DBException() {

    }
}
