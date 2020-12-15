package ua.dnipro.epam.homework.service;

import ua.dnipro.epam.homework.dto.GradeWithTestName;
import ua.dnipro.epam.homework.dto.QuestionContentWithAnswer;
import ua.dnipro.epam.homework.dto.TestSubjectComplexity;
import ua.dnipro.epam.homework.entity.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface TestService {

    Test findById(Long testId);

    List<Test> findAll(String lang);

    List<TestSubjectComplexity> findAllForChoose();

    List<GradeWithTestName> findByUserID(Long userId);

    Test create (String name, int time, int numberOfQuestions, long subjectId, long complexityId);

    void deleteById(String reqTestId);

    void passingTest(HttpServletRequest request, HttpSession session, List<QuestionContentWithAnswer> list);
}
