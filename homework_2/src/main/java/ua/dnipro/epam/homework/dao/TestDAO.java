package ua.dnipro.epam.homework.dao;

import ua.dnipro.epam.homework.entity.Test;

public interface TestDAO extends GeneralDAO<Test, Long> {

    void deleteByName (String name);

}
