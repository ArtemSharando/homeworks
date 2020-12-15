package ua.dnipro.epam.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.dnipro.epam.homework.entity.User;
import ua.dnipro.epam.homework.service.impl.TestServiceImpl;
import ua.dnipro.epam.homework.service.impl.UserServiceImpl;

import javax.servlet.http.HttpSession;

import static ua.dnipro.epam.homework.manager.Links.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserServiceImpl userServiceImpl;
    private final TestServiceImpl testServiceImpl;

    @GetMapping
    public ModelAndView profilePage(HttpSession session){
        User user = userServiceImpl.findByUsername(session.getAttribute(USERNAME).toString());
        ModelAndView modelAndView = new ModelAndView("profile");
        modelAndView.addObject(LIST, testServiceImpl.findByUserID(user.getId()));
        return modelAndView;
    }
}
