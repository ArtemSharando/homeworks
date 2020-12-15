package ua.dnipro.epam.homework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

import static ua.dnipro.epam.homework.manager.Links.LANG;

@Controller
@RequestMapping({"/", "/home"})
public class HomeController {

    @GetMapping
    public String HomePage() {
        return "home";
    }


    @PostMapping
    public String getLang(@RequestParam String lang, HttpSession session) {
        session.setAttribute(LANG, lang);
        return "redirect:/home";
    }

}
