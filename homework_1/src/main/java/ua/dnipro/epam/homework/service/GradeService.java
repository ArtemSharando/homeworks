package ua.dnipro.epam.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.dnipro.epam.homework.dao.impl.GradeDAOImpl;
import ua.dnipro.epam.homework.entity.Grade;

@RequiredArgsConstructor
@Service
public class GradeService {

    GradeDAOImpl gradeDAO = new GradeDAOImpl();

    public Grade create (String result, Long testId, Long userId){
        Grade grade = Grade.builder()
                .result(result)
                .testId(testId)
                .userId(userId)
                .build();
        return gradeDAO.create(grade);
    }
}
