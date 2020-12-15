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
import static org.hamcrest.Matchers.is;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {

    private final HomeController homeController = new HomeController();

    private MockHttpSession mockHttpSession = new MockHttpSession();

    private MockMvc mockMvc;

    @Before
    public void setup() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/view/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(homeController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void shouldGetHomePage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/home")
                .accept(MediaType.TEXT_HTML)).andExpect(status().isOk());
    }

    @Test
    public void shouldRedirectToHomePage() throws Exception {
        String paramLang = "lang";
        String LangValue = "en";

        mockMvc.perform(MockMvcRequestBuilders.post("/home")
                .accept(MediaType.TEXT_HTML)
                .session(mockHttpSession)
                .param(paramLang, LangValue))
                .andExpect(status().isFound());

        assertThat(mockHttpSession.getAttribute(paramLang), is(LangValue));
    }
}

