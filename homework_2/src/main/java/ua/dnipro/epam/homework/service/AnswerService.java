package ua.dnipro.epam.homework.service;

import ua.dnipro.epam.homework.entity.Question;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AnswerService {

    void createListOfAnswer (List<Question> questions, HttpServletRequest request, Long testId);

    List<?> convertObjectToList(Object obj);
}
