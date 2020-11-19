package ua.dnipro.epam.homework.service;

import ua.dnipro.epam.homework.entity.Grade;

public interface GradeService {

    Grade create (String result, Long testId, Long userId);
}
