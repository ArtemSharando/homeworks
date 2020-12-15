package ua.dnipro.epam.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import ua.dnipro.epam.homework.controller.TestController;
import ua.dnipro.epam.homework.dao.impl.ComplexityDAOImpl;
import ua.dnipro.epam.homework.dao.impl.GradeDAOImpl;
import ua.dnipro.epam.homework.dao.impl.SubjectDAOImpl;
import ua.dnipro.epam.homework.dao.impl.TestDAOImpl;
import ua.dnipro.epam.homework.dto.GradeWithTestName;
import ua.dnipro.epam.homework.dto.QuestionContentWithAnswer;
import ua.dnipro.epam.homework.dto.TestSubjectComplexity;
import ua.dnipro.epam.homework.entity.Answer;
import ua.dnipro.epam.homework.entity.Test;
import ua.dnipro.epam.homework.service.GradeService;
import ua.dnipro.epam.homework.service.QuestionService;
import ua.dnipro.epam.homework.service.TestService;
import ua.dnipro.epam.homework.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TestServiceImpl implements TestService {

    private final TestDAOImpl testDAOImpl = new TestDAOImpl();
    private final GradeDAOImpl gradeDAOImpl = new GradeDAOImpl();
    private final UserService userService = new UserServiceImpl();
    private final GradeService gradeService = new GradeServiceImpl();
    private final QuestionService questionService = new QuestionServiceImpl();

    private final static Logger LOG = Logger.getLogger(TestServiceImpl.class);

    public List<Test> findAll(String lang) {
        return testDAOImpl.findAll(lang);
    }

    public Test findById(Long testId) {
        return testDAOImpl.findById(testId);
    }

    public List<GradeWithTestName> findByUserID(Long userId) {
        List<GradeWithTestName> list = new ArrayList<>();
        gradeDAOImpl.findByUserId(userId).forEach(grade -> list.add(GradeWithTestName
                .builder()
                .result(grade.getResult())
                .testName(testDAOImpl.findById(grade.getTestId()).getName())
                .build()));
        return list;
    }

    public List<TestSubjectComplexity> findAllForChoose() {

        List<TestSubjectComplexity> list = new ArrayList<>();
        TestServiceImpl testServiceImpl = new TestServiceImpl();
        SubjectDAOImpl subjectDAOImpl = new SubjectDAOImpl();
        ComplexityDAOImpl complexityDAOImpl = new ComplexityDAOImpl();

        testServiceImpl.findAll("en").forEach(test -> list.add(TestSubjectComplexity
                .builder()
                .id(test.getId())
                .name(test.getName())
                .numberOfQuestion(test.getNumberOfQuestions())
                .subject(subjectDAOImpl.findById(test.getSubjectId()).getName())
                .complexity(complexityDAOImpl.findById(test.getComplexityId()).getName())
                .build()));
        return list;
    }

    public Test create(String name, int time, int numberOfQuestions, long subjectId, long complexityId) {
        Test test = Test.builder()
                .name(name)
                .time(time)
                .numberOfQuestions(numberOfQuestions)
                .subjectId(subjectId)
                .complexityId(complexityId)
                .build();
        return testDAOImpl.create(test);
    }

    public void deleteById(String reqTestId) {
        testDAOImpl.deleteById(Long.parseLong(reqTestId));
    }

    public void passingTest(HttpServletRequest request, HttpSession session, List<QuestionContentWithAnswer> list) {
        int k = 0;
        for (QuestionContentWithAnswer questionContentWithAnswer : list) {
            for (Answer answer : questionContentWithAnswer.getAnswerOfQ()) {
                String correctAnswerS = request.getParameter("correctAnswer" + answer.getId());
                if (Boolean.parseBoolean(correctAnswerS)) {
                    k++;
                }
            }
        }
        String result = questionService.percent(list.size(), k);
        String username = session.getAttribute("username").toString();
        gradeService.create(result,
                Long.parseLong(session.getAttribute("testId").toString()),
                userService.findByUsername(username).getId());
        LOG.info(userService.findByUsername(username).getUsername() + " passed the test " + result + "%");
    }
}
