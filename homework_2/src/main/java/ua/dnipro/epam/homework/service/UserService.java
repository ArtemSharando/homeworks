package ua.dnipro.epam.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import ua.dnipro.epam.homework.dao.impl.UserDAOImpl;
import ua.dnipro.epam.homework.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static ua.dnipro.epam.homework.manager.QuerySQL.*;

@RequiredArgsConstructor
@Service
public class UserService {

    private UserDAOImpl userDAOImpl = new UserDAOImpl();

    public User findByUsername (String username){
        return userDAOImpl.findByUsername(username).orElse(null);
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

    public User create ( String username,String password,String name,String surname){
        User user = User.builder()
                .username(username)
                .password(password)
                .name(name)
                .surname(surname)
                .build();
        return userDAOImpl.create(user);
    }

    public User statusUser(String requestUserId, boolean status){
        return userDAOImpl.ban(Long.parseLong(requestUserId),status);
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

}
