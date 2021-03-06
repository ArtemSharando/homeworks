package ua.dnipro.epam.homework.controller;

import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.dnipro.epam.homework.entity.User;
import ua.dnipro.epam.homework.service.impl.UserServiceImpl;

import java.util.Map;

import static ua.dnipro.epam.homework.manager.Links.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final UserServiceImpl userServiceImpl;
    private static final Logger LOG = Logger.getLogger(RegistrationController.class);

    @GetMapping
    public String showRegistration(){
        return "registration";
    }

    @PostMapping
    public String register(@RequestParam Map<String, String> request){
        User user = userServiceImpl.create(request.get(USERNAME),request.get(PASSWORD),request.get(NAME),request.get(SURNAME));
        LOG.trace("Register new user with username: --> " + user.getUsername());
        return "redirect:/home";
    }
}
