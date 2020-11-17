package ua.dnipro.epam.homework.dao;

import ua.dnipro.epam.homework.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO extends GeneralDAO<User, Long> {

    Optional <User> findByUsername (String username);

    User ban (Long id, Boolean status);

}
