package ua.dnipro.epam.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.dnipro.epam.homework.dao.impl.AnswerDAOImpl;
import ua.dnipro.epam.homework.entity.Answer;
import ua.dnipro.epam.homework.entity.Question;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AnswerServiceImpl {

    private final AnswerDAOImpl answerDAO = new AnswerDAOImpl();

    public void createListOfAnswer (List<Question> questions, HttpServletRequest request, Long testId){
        for (Question question : questions) {
            for (int i = 1; i <= question.getNumberOfAnswer(); i++) {
                String variant = request.getParameter("variant" + question.getCounterQuestion() + i);
                String correctAnswer = request.getParameter("correctAnswer" + question.getCounterQuestion() + i);
                if(correctAnswer==null){
                    correctAnswer="false";
                }

                Answer answer = Answer.builder()
                        .variant(variant)
                        .counterAnswer(i)
                        .correctAnswer(Boolean.parseBoolean(correctAnswer))
                        .questionId(question.getId())
                        .build();
                answerDAO.create(answer,question.getId(),testId);
            }
        }
        questions.clear();
    }

    //This method is used in this project to convert an object from a session to a collection.
    public static List<?> convertObjectToList(Object obj) {
        List<Question> list = new ArrayList<>();
        if (obj.getClass().isArray()) {
            list = Arrays.asList((Question[])obj);
        } else if (obj instanceof Collection) {
            list = new ArrayList<>((Collection<Question>)obj);
        }
        return list;
    }
}
