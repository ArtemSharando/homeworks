package ua.dnipro.epam.homework.controller;

import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.dnipro.epam.homework.entity.RoleName;
import ua.dnipro.epam.homework.entity.User;
import ua.dnipro.epam.homework.exception.EmptyLoginOrPasswordException;
import ua.dnipro.epam.homework.exception.WrongLoginOrPasswordException;
import ua.dnipro.epam.homework.service.UserService;
import ua.dnipro.epam.homework.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static ua.dnipro.epam.homework.manager.Links.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/login")
public class LoginController {

    private static final Logger LOG = Logger.getLogger(LoginController.class);

    private final UserService userServiceImpl;

    @GetMapping
    public String LoginPage() {
        return "login";
    }

    @PostMapping
    public String loginUser(HttpServletRequest request, HttpSession session){
        String username = request.getParameter(USERNAME);
        LOG.trace("Request parameter: login --> " + username);

        String password = request.getParameter(PASSWORD);

        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            throw new EmptyLoginOrPasswordException("Login/password cannot be empty");
        }

        User user = userServiceImpl.findByUsername(username.toLowerCase());
        LOG.trace("Found in DB: user --> " + user);


        if (user != null && user.getUsername().equals(username.toLowerCase()) && user.getPassword().equals(password)) {
            String userRole = user.getRoleId().getRole();
            String lang = session.getAttribute(LANG).toString();
            session.setAttribute(USERNAME, username);
            session.setAttribute(NAME, user.getName());
            session.setAttribute(IS_LOGGED, user);
            session.setAttribute(USER, user);

            LOG.trace("Set the session attribute: user --> " + user);
            session.setAttribute(USER_ROLE, userRole);

            LOG.trace("Set the session attribute: userRole --> " + userRole);
            session.setAttribute(LANG, lang);

            session.setMaxInactiveInterval(30 * 60);
            LOG.info("User " + user + " logged as " + userRole.toLowerCase());
            if (user.getRoleId().getRole().equals(RoleName.ADMIN.getName())) {
                session.setAttribute(IS_ADMIN, RoleName.ADMIN.getName());
                LOG.trace("Set the session attribute: isAdmin --> true");
            }

            LOG.debug("LoginServlet finished");

            return "redirect:/home";
        } else {
            throw new WrongLoginOrPasswordException("Wrong Username/password");
        }
    }
}

