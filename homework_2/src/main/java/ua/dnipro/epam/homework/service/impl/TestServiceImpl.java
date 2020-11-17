package ua.dnipro.epam.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.dnipro.epam.homework.dao.impl.ComplexityDAOImpl;
import ua.dnipro.epam.homework.dao.impl.GradeDAOImpl;
import ua.dnipro.epam.homework.dao.impl.SubjectDAOImpl;
import ua.dnipro.epam.homework.dao.impl.TestDAOImpl;
import ua.dnipro.epam.homework.dto.GradeWithTestName;
import ua.dnipro.epam.homework.dto.TestSubjectComplexity;
import ua.dnipro.epam.homework.entity.Test;
import ua.dnipro.epam.homework.service.TestService;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TestServiceImpl implements TestService {

    private TestDAOImpl testDAOImpl = new TestDAOImpl();

    private GradeDAOImpl gradeDAOImpl = new GradeDAOImpl();

    @Override
    public List<Test> findAll(String lang) {
        return testDAOImpl.findAll(lang);
    }

    @Override
    public Test findById(Long testId){
        return testDAOImpl.findById(testId);
    }

    @Override
    public List<GradeWithTestName> findByUserID(Long userId) {
        List<GradeWithTestName> list = new ArrayList<>();
        gradeDAOImpl.findByUserId(userId).stream().forEach(grade -> list.add(GradeWithTestName
                .builder()
                .result(grade.getResult())
                .testName(testDAOImpl.findById(grade.getTestId()).getName())
                .build()));
        return list;
    }

    @Override
    public List<TestSubjectComplexity> findAllForChoose() {

        List<TestSubjectComplexity> list = new ArrayList<>();
        TestServiceImpl testServiceImpl = new TestServiceImpl();
        SubjectDAOImpl subjectDAOImpl = new SubjectDAOImpl();
        ComplexityDAOImpl complexityDAOImpl = new ComplexityDAOImpl();

        testServiceImpl.findAll("en").stream().forEach(test -> list.add(TestSubjectComplexity
                .builder()
                .id(test.getId())
                .name(test.getName())
                .numberOfQuestion(test.getNumberOfQuestions())
                .subject(subjectDAOImpl.findById(test.getSubjectId()).getName())
                .complexity(complexityDAOImpl.findById(test.getComplexityId()).getName())
                .build()));
        return list;
    }

    @Override
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

    @Override
    public void deleteById(String reqTestId){
        testDAOImpl.deleteById(Long.parseLong(reqTestId));
    }
}
