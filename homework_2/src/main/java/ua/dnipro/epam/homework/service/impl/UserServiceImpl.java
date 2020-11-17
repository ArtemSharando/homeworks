package ua.dnipro.epam.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.dnipro.epam.homework.annotation.Timed;
import ua.dnipro.epam.homework.dao.UserDAO;
import ua.dnipro.epam.homework.dao.impl.UserDAOImpl;
import ua.dnipro.epam.homework.entity.User;
import ua.dnipro.epam.homework.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static ua.dnipro.epam.homework.manager.QuerySQL.*;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Timed
    @Override
    public User findByUsername (String username){
        return userDAO.findByUsername(username).orElse(null);
    }

    @Override
    public List<User> findAll(){
        return userDAO.findAll(SELECT_FROM_USER_ALL);
    }

    @Override
    public List<User> findAllActive(){
        return userDAO.findAll(SELECT_FROM_USER_ALL_WHERE_STATUS_TRUE);
    }

    @Override
    public List<User> findAllInactive(){
       return userDAO.findAll(SELECT_FROM_USER_ALL_WHERE_STATUS_FALSE);
    }

    @Override
    public User create ( String username,String password,String name,String surname){
        User user = User.builder()
                .username(username)
                .password(password)
                .name(name)
                .surname(surname)
                .build();
        return userDAO.create(user);
    }

    @Override
    public User statusUser(String requestUserId, boolean status){
        return userDAO.ban(Long.parseLong(requestUserId),status);
    }

    @Override
    public void update(List <User> users, HttpServletRequest request){
        for(User user: users){
            User entity = User.builder()
                    .username(request.getParameter("username" + user.getId()))
                    .password(request.getParameter("password"+ user.getId()))
                    .name(request.getParameter("name" + user.getId()))
                    .surname(request.getParameter("surname" + user.getId()))
                    .build();
            userDAO.update(entity, user.getId());
        }
    }

}
