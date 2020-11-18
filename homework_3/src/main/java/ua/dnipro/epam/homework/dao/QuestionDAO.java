package ua.dnipro.epam.homework.dao;

import ua.dnipro.epam.homework.entity.Question;

public interface QuestionDAO extends GeneralDAO<Question, Long> {

    void deleteByTestId(Long testId);
}
