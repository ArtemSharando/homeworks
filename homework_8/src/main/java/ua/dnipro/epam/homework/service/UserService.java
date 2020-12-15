package ua.dnipro.epam.homework.service;

import ua.dnipro.epam.homework.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {

    User findByUsername(String username);

    List<User> findAll();

    List<User> findAllActive();

    List<User> findAllInactive();

    User create(String username, String password, String name, String surname);

    void statusUser(String requestUserId, boolean status);

    void update(List<User> users, HttpServletRequest request);
}
