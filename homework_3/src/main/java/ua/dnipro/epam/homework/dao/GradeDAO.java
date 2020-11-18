package ua.dnipro.epam.homework.dao;

import ua.dnipro.epam.homework.entity.Grade;

import java.util.List;

public interface GradeDAO extends GeneralDAO<Grade, Long> {
    List<Grade> findByUserId (Long id);
}
