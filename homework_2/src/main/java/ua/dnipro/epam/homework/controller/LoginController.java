package ua.dnipro.epam.homework.controller;

import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.dnipro.epam.homework.entity.RoleName;
import ua.dnipro.epam.homework.entity.User;
import ua.dnipro.epam.homework.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RequiredArgsConstructor
@Controller
@RequestMapping("/login")
public class LoginController {

    private static final Logger LOG = Logger.getLogger(LoginController.class);

    private final UserServiceImpl userServiceImpl;

    @GetMapping
    public String LoginPage() {
        return "login";
    }

    @PostMapping
    public String loginUser(HttpServletRequest request, HttpSession session) throws IOException {
        String username = request.getParameter("username");
        LOG.trace("Request parameter: login --> " + username);

        String password = request.getParameter("password");

        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            throw new IOException("Login/password cannot be empty");
        }

        User user = userServiceImpl.findByUsername(username);
        LOG.trace("Found in DB: user --> " + user);


        if (user != null && user.getUsername().equals(username) && user.getPassword().equals(password)) {
            String userRole = user.getRoleId().getRole();
            String lang = session.getAttribute("lang").toString();
            session.setAttribute("username", username);
            session.setAttribute("name", user.getName());
            session.setAttribute("isLogged", user);
            session.setAttribute("user", user);

            LOG.trace("Set the session attribute: user --> " + user);
            session.setAttribute("userRole", userRole);

            LOG.trace("Set the session attribute: userRole --> " + userRole);
            session.setAttribute("lang", lang);

            session.setMaxInactiveInterval(30 * 60);
            LOG.info("User " + user + " logged as " + userRole.toLowerCase());
            if (user.getRoleId().getRole().equals(RoleName.ADMIN.getName())) {
                session.setAttribute("isAdmin", RoleName.ADMIN.getName());
                LOG.trace("Set the session attribute: isAdmin --> true");
            }

            LOG.debug("LoginServlet finished");

            return "redirect:/home";
        } else {
            return "index";
        }
    }
}

