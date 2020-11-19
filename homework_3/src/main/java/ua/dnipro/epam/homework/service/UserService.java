package ua.dnipro.epam.homework.service;

import ua.dnipro.epam.homework.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {

    public User findByUsername (String username);
    public List<User> findAll();
    public List<User> findAllActive();
    public List<User> findAllInactive();
    public User create ( String username,String password,String name,String surname);
    public void statusUser(String requestUserId, boolean status);
    public void update(List <User> users, HttpServletRequest request);
}
