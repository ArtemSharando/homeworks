package ua.dnipro.epam.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.dnipro.epam.homework.entity.User;
import ua.dnipro.epam.homework.service.TestService;
import ua.dnipro.epam.homework.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;
    private final TestService testService;

    @GetMapping
    public String profilePage(HttpServletRequest request, HttpSession session){
        User user = userService.findByUsername(session.getAttribute("username").toString());
        request.setAttribute("list", testService.findByUserID(user.getId()));
        return "profile";
    }
}
