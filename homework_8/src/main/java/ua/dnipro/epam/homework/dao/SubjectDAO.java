package ua.dnipro.epam.homework.dao;

import ua.dnipro.epam.homework.entity.Subject;

public interface SubjectDAO extends GeneralDAO<Subject, Long> {

    Subject findByName (String name);

}
