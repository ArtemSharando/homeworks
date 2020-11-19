package ua.dnipro.epam.homework.exception;

public class EmptyLoginOrPasswordException extends RuntimeException{
    public EmptyLoginOrPasswordException(String message) {
        super(message);
    }
}
