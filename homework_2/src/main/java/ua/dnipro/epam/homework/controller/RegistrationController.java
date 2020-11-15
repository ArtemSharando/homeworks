package ua.dnipro.epam.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.dnipro.epam.homework.entity.User;
import ua.dnipro.epam.homework.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;

    @GetMapping
    public String showRegistration(){
        return "registration";
    }

    @PostMapping
    public String register(@RequestParam Map<String, String> request){
        userService.create(request.get("username"),request.get("password"),request.get("name"),request.get("surname"));
        return "redirect:/home";
    }
}
