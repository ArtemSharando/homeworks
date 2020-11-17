package ua.dnipro.epam.homework.service;

import ua.dnipro.epam.homework.entity.Subject;

import java.util.List;

public interface SubjectService {

    List<Subject> findAll(String lang);
}
