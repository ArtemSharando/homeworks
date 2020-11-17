package ua.dnipro.epam.homework.service;

import ua.dnipro.epam.homework.dto.QuestionContentWithAnswer;
import ua.dnipro.epam.homework.entity.Question;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface QuestionService {

    List<QuestionContentWithAnswer> findByTestId(Long testId);

    List <Question> createListOfQ(int numberOfQ, HttpServletRequest request, String content, String number, long testId);

    String percent (double numberOfQ, double numberOfA);
}
