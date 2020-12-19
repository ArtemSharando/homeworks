package ua.dnipro.epam.homework.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ua.dnipro.epam.homework.dao.UserDAO;
import ua.dnipro.epam.homework.entity.User;
import ua.dnipro.epam.homework.service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static ua.dnipro.epam.homework.manager.QuerySQL.SELECT_FROM_USER_ALL;


@Slf4j
public class UserServiceTest {

    private final UserDAO userDAO = Mockito.mock(UserDAO.class);

    private User user;

    private long id;

    private String username;

    private List<User> users;

    private final UserService userService = new UserServiceImpl();

    @Before
    public void setUp() {
        id = 84L;
        username = "user16";
        user = User
                .builder()
                .id(id)
                .username(username)
                .password("12345")
                .name("User16")
                .build();

        users = new ArrayList<>();
        users.add(user);
        users.add(User
                .builder()
                .id(85L)
                .name("User15")
                .username("user15")
                .password("12345")
                .build());
    }

    @Test
    public void findByUsername() {
        Mockito.when(userDAO.findByUsername(Mockito.anyString())).thenReturn(Optional.ofNullable(user));
        User userCreated = userService.findByUsername(user.getUsername());
        Assert.assertEquals(user.getUsername(), userCreated.getUsername());
    }

    @Test
    public void shouldCreateUser() {
        User user = this.user;
        when(userDAO.create(any())).thenReturn(user);

        User foundUser = userService.create(username,user.getPassword(),username,username);
        foundUser.setId(id);

        assertThat(foundUser, hasProperty("username", equalTo(username)));
    }


    @Test
    public void shouldFindAllUsers() {
        List<User> users = this.users;
        when(userDAO.findAll(SELECT_FROM_USER_ALL)).thenReturn(users);

        Collection<User> foundUsers = userService.findAll();

        assertThat(foundUsers, hasSize(8));

        users.forEach(
                user -> assertThat(foundUsers, hasItem(allOf(
                        hasProperty("id", is(user.getId())),
                        hasProperty("name", is(user.getName())),
                        hasProperty("username", is(user.getUsername())),
                        hasProperty("password", is(user.getPassword()))
                )))
        );
    }

}
