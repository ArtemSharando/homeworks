package ua.dnipro.epam.homework.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final static Logger LOG = Logger.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(SQLException.class)
    public String handleSQLException (HttpServletRequest request, Exception e){
        LOG.info("SQLException :: URL =" + request.getRequestURL());
        request.setAttribute("ex", e);
        return "error/errorPage";
    }
}
