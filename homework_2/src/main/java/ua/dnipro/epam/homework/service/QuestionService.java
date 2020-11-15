package ua.dnipro.epam.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.dnipro.epam.homework.dao.impl.AnswerDAOImpl;
import ua.dnipro.epam.homework.dao.impl.QuestionDAOImpl;
import ua.dnipro.epam.homework.dto.QuestionContentWithAnswer;
import ua.dnipro.epam.homework.entity.Question;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class QuestionService {
    QuestionDAOImpl questionDAOImpl = new QuestionDAOImpl();
    AnswerDAOImpl answerDAOImpl = new AnswerDAOImpl();

    public List<QuestionContentWithAnswer> findByTestId(Long testId) {
        List <QuestionContentWithAnswer> questionContentWithAnswers = new ArrayList<>();
        questionDAOImpl.findAllTestId(testId).stream().forEach(question -> questionContentWithAnswers.add(QuestionContentWithAnswer
                .builder()
                .content(question.getContent())
                .counterQuestion(question.getCounterQuestion())
                .answerOfQ(answerDAOImpl.findByQuestionId(question.getId()))
                .build()));
        return questionContentWithAnswers;
    }

    public List <Question> createListOfQ(int numberOfQ, HttpServletRequest request, String content, String number, long testId){
        List <Question> questions = new ArrayList<>();
        for (int i = 1; i <= numberOfQ; i++) {
            Question question = Question.builder()
                    .content(request.getParameter(content + i))
                    .counterQuestion(i)
                    .numberOfAnswer(Integer.parseInt(request.getParameter(number + i)))
                    .testId(testId)
                    .build();
            questionDAOImpl.create(question, testId);
            questions.add(question);
        }
        return questions;
    }

    public String percent (double numberOfQ, double numberOfA){
        DecimalFormat df = new DecimalFormat("###.##");
        int k = 100;
        double result = (numberOfA * k) / numberOfQ;
        return df.format(result);
    }
}
