package ua.dnipro.epam.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.dnipro.epam.homework.dao.impl.GradeDAOImpl;
import ua.dnipro.epam.homework.entity.Grade;
import ua.dnipro.epam.homework.service.GradeService;

@RequiredArgsConstructor
@Service
public class GradeServiceImpl implements GradeService {

    GradeDAOImpl gradeDAO = new GradeDAOImpl();

    @Override
    public Grade create (String result, Long testId, Long userId){
        Grade grade = Grade.builder()
                .result(result)
                .testId(testId)
                .userId(userId)
                .build();
        return gradeDAO.create(grade);
    }
}
