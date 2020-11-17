package ua.dnipro.epam.homework.service;

import ua.dnipro.epam.homework.dto.GradeWithTestName;
import ua.dnipro.epam.homework.dto.TestSubjectComplexity;
import ua.dnipro.epam.homework.entity.Test;

import java.util.List;

public interface TestService {

    Test findById(Long testId);

    List<Test> findAll(String lang);

    List<TestSubjectComplexity> findAllForChoose();

    List<GradeWithTestName> findByUserID(Long userId);

    Test create (String name, int time, int numberOfQuestions, long subjectId, long complexityId);

    void deleteById(String reqTestId);

}
