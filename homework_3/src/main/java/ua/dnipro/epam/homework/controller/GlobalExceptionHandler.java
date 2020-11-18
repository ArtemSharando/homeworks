package ua.dnipro.epam.homework.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import ua.dnipro.epam.homework.exception.EntityNotFoundException;

import javax.servlet.http.HttpServletRequest;

import static ua.dnipro.epam.homework.exception.Messages.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final static Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(NoHandlerFoundException e, HttpServletRequest request) {
        logger.error(e);
        request.setAttribute(EX, SOMETHING_WENT_WRONG);
        return ERROR_PAGE;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handle404Exception(NoHandlerFoundException e, HttpServletRequest request) {
        logger.error(e);
        request.setAttribute(EX, PAGE_NOT_FOUND_404);
        return ERROR_PAGE;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public String handleEntityNotFoundException(HttpServletRequest request, Exception e) {
        logger.error(e.getMessage(), e);
        request.setAttribute(EX, ENTITY_NOT_FOUND);
        return ERROR_PAGE;
    }

}

