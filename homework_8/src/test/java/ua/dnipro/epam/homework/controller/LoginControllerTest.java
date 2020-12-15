package ua.dnipro.epam.homework.controller;



import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ua.dnipro.epam.homework.entity.Role;
import ua.dnipro.epam.homework.entity.User;
import ua.dnipro.epam.homework.service.UserService;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    private final UserService userService = mock(UserService.class);

    private final LoginController loginController = new LoginController(userService);

    private MockMvc mockMvc;

    private String username;

    private String password;

    private User user;

    @Before
    public void setUp() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/view/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(loginController)
                .setViewResolvers(viewResolver)
                .build();

        long id = 99L;
        username = "ad";
        password = "123";
        Role role = new Role();
        role.setRole("ADMIN");
        role.setId_roles(1L);
        user = User
                .builder()
                .id(id)
                .username(username)
                .password(password)
                .name(username)
                .roleId(role)
                .build();
    }

    @Test
    public void getLoginPageShouldReturnLoginPage() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/login")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk());
    }


    @Test
    public void loginUserShouldReturnHomePage() throws Exception {

        given(this.userService.findByUsername(anyString())).willReturn(user);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/login")
                        .accept(MediaType.TEXT_HTML)
                        .param("username", username)
                        .param("password", password)
                        .session(new MockHttpSession())
                        .sessionAttr("lang", "en"))
                .andExpect(status().isFound());

    }
}


