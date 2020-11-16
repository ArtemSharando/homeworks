package ua.dnipro.epam.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.dnipro.epam.homework.entity.User;
import ua.dnipro.epam.homework.service.impl.TestServiceImpl;
import ua.dnipro.epam.homework.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserServiceImpl userServiceImpl;
    private final TestServiceImpl testServiceImpl;

    @GetMapping
    public String profilePage(HttpServletRequest request, HttpSession session){
        User user = userServiceImpl.findByUsername(session.getAttribute("username").toString());
        request.setAttribute("list", testServiceImpl.findByUserID(user.getId()));
        return "profile";
    }
}
