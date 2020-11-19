package ua.dnipro.epam.homework.service;

import ua.dnipro.epam.homework.entity.Complexity;

import java.util.List;

public interface ComplexityService {

    List<Complexity> findAll(String lang);
}
