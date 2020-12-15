package ua.dnipro.epam.homework.exception;

public class WrongLoginOrPasswordException extends RuntimeException {
    public WrongLoginOrPasswordException(String message) {
        super(message);
    }

}
