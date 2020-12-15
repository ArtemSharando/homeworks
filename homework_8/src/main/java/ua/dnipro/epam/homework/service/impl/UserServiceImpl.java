package ua.dnipro.epam.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.dnipro.epam.homework.dao.impl.UserDAOImpl;
import ua.dnipro.epam.homework.entity.User;
import ua.dnipro.epam.homework.exception.NotCreateException;
import ua.dnipro.epam.homework.exception.UserNotFoundException;
import ua.dnipro.epam.homework.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static ua.dnipro.epam.homework.manager.QuerySQL.*;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserDAOImpl userDAOImpl = new UserDAOImpl();

    public User findByUsername (String username){
        return userDAOImpl.findByUsername(username.toLowerCase()).orElseThrow(UserNotFoundException::new);
    }

    public List<User> findAll(){
        return userDAOImpl.findAll(SELECT_FROM_USER_ALL);
    }

    public List<User> findAllActive(){
        return userDAOImpl.findAll(SELECT_FROM_USER_ALL_WHERE_STATUS_TRUE);
    }
    public List<User> findAllInactive(){
       return userDAOImpl.findAll(SELECT_FROM_USER_ALL_WHERE_STATUS_FALSE);
    }

    public User create (String username, String password, String name, String surname) {
        User user = User.builder()
                .username(username.toLowerCase())
                .password(password)
                .name(UserServiceImpl.firstUpperCase(name))
                .surname(UserServiceImpl.firstUpperCase(surname))
                .build();
        if(password != null){
            return userDAOImpl.create(user);
        }
        else throw new NotCreateException("Can`t create this user");
    }

    public void statusUser(String requestUserId, boolean status){
        userDAOImpl.ban(Long.parseLong(requestUserId), status);
    }

    public void update(List <User> users, HttpServletRequest request){
        for(User user: users){
            User entity = User.builder()
                    .username(request.getParameter("username" + user.getId()))
                    .password(request.getParameter("password"+ user.getId()))
                    .name(request.getParameter("name" + user.getId()))
                    .surname(request.getParameter("surname" + user.getId()))
                    .build();
            userDAOImpl.update(entity, user.getId());
        }
    }
    private static String firstUpperCase(String word){
        return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    }

}
