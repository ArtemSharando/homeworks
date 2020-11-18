package ua.dnipro.epam.homework.controller;

import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
@RequestMapping("/logout")
public class LogoutController {
    private static final Logger LOG = Logger.getLogger(LogoutController.class);

    @GetMapping
    public String logout(HttpSession session){
        if(session != null){
            LOG.debug("Session " + session.getId() + " is over");
            session.invalidate();
        }
        return "redirect:/home";
    }
}
