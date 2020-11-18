package ua.dnipro.epam.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.dnipro.epam.homework.dao.impl.ComplexityDAOImpl;
import ua.dnipro.epam.homework.dao.impl.GradeDAOImpl;
import ua.dnipro.epam.homework.dao.impl.SubjectDAOImpl;
import ua.dnipro.epam.homework.dao.impl.TestDAOImpl;
import ua.dnipro.epam.homework.dto.GradeWithTestName;
import ua.dnipro.epam.homework.dto.QuestionContentWithAnswer;
import ua.dnipro.epam.homework.dto.TestSubjectComplexity;
import ua.dnipro.epam.homework.entity.Answer;
import ua.dnipro.epam.homework.entity.Test;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TestService {

    private TestDAOImpl testDAOImpl = new TestDAOImpl();

    private GradeDAOImpl gradeDAOImpl = new GradeDAOImpl();

    public List<Test> findAll(String lang) {
        return testDAOImpl.findAll(lang);
    }

    public Test findById(Long testId){
        return testDAOImpl.findById(testId);
    }

    public List<GradeWithTestName> findByUserID(Long userId) {
        List<GradeWithTestName> list = new ArrayList<>();
        gradeDAOImpl.findByUserId(userId).stream().forEach(grade -> list.add(GradeWithTestName
                .builder()
                .result(grade.getResult())
                .testName(testDAOImpl.findById(grade.getTestId()).getName())
                .build()));
        return list;
    }

    public List<TestSubjectComplexity> findAllForChoose() {

        List<TestSubjectComplexity> list = new ArrayList<>();
        TestService testService = new TestService();
        SubjectDAOImpl subjectDAOImpl = new SubjectDAOImpl();
        ComplexityDAOImpl complexityDAOImpl = new ComplexityDAOImpl();

        testService.findAll("en").stream().forEach(test -> list.add(TestSubjectComplexity
                .builder()
                .id(test.getId())
                .name(test.getName())
                .numberOfQuestion(test.getNumberOfQuestions())
                .subject(subjectDAOImpl.findById(test.getSubjectId()).getName())
                .complexity(complexityDAOImpl.findById(test.getComplexityId()).getName())
                .build()));
        return list;
    }
    public Test create (String name, int time, int numberOfQuestions, long subjectId, long complexityId){
        Test test = Test.builder()
                .name(name)
                .time(time)
                .numberOfQuestions(numberOfQuestions)
                .subjectId(subjectId)
                .complexityId(complexityId)
                .build();
        return testDAOImpl.create(test);
    }

    public void deleteById(String reqTestId){
        testDAOImpl.deleteById(Long.parseLong(reqTestId));
    }
}
