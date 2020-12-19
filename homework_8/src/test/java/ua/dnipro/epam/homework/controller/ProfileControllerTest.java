package ua.dnipro.epam.homework.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.context.WebApplicationContext;
import ua.dnipro.epam.homework.AbstractBaseSpringTest;
import ua.dnipro.epam.homework.dto.GradeWithTestName;
import ua.dnipro.epam.homework.service.TestService;
import ua.dnipro.epam.homework.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
public class ProfileControllerTest extends AbstractBaseSpringTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockHttpSession mockHttpSession;

    @Autowired
    private TestService testService;

    @Autowired
    private UserService userService;


    private final Map<String, Object> sessAtr = new HashMap<>();


    private List<GradeWithTestName> grades = new ArrayList<>();

    private GradeWithTestName grade;

    String username;

    @Before
    public void setUp() {
        grade = GradeWithTestName.builder()
                .result("100")
                .testName("Testing3")
                .build();
        username = "user2";
        mockHttpSession = new MockHttpSession(webApplicationContext.getServletContext());
        sessAtr.put("username", username);
        sessAtr.put("list", testService.findByUserID(userService.findByUsername(username).getId()));
        grades.add(grade);
    }

    @Test
    public void getProfileShouldReturnProfileDetails() throws Exception {
        mockMvc.perform(
                get("/profile")
                        .accept(MediaType.TEXT_HTML)
                        .session(mockHttpSession)
                        .sessionAttrs(sessAtr))
                .andExpect(status().isOk());
        Assert.assertEquals(username, sessAtr.get("username"));
        Assert.assertEquals(grades, sessAtr.get("list"));
    }
}
