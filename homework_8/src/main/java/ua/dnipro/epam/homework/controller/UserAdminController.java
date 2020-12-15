package ua.dnipro.epam.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.dnipro.epam.homework.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

import static ua.dnipro.epam.homework.manager.Links.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/userAdmin")
public class UserAdminController {

    private final UserServiceImpl userServiceImpl;

    @GetMapping
    public String userAdminPage(){
        return "userAdmin";
    }

    @GetMapping("ban")
    public ModelAndView userAdminBanPage(){
        ModelAndView modelAndView = new ModelAndView("ban");
        modelAndView.addObject(USERS, userServiceImpl.findAllActive());
        return modelAndView;
    }

    @PostMapping("ban")
    public String userAdminBan(HttpServletRequest request){
        userServiceImpl.statusUser(request.getParameter(USER_ID), false);
        return "redirect:/userAdmin";
    }

    @GetMapping("unban")
    public ModelAndView userAdminUnBanPage(){
        ModelAndView modelAndView = new ModelAndView("unban");
        modelAndView.addObject(USERS, userServiceImpl.findAllInactive());
        return modelAndView;
    }

    @PostMapping("unban")
    public String userAdminUnban(HttpServletRequest request){
        userServiceImpl.statusUser(request.getParameter(USER), true);
        return "redirect:/userAdmin";
    }

    @GetMapping("edit")
    public ModelAndView userAdminEditPage(){
        ModelAndView modelAndView = new ModelAndView("editUser");
        modelAndView.addObject(USERS, userServiceImpl.findAll());
        return modelAndView;
    }

    @PostMapping("edit")
    public String userAdminEdit(HttpServletRequest request){
        userServiceImpl.update(userServiceImpl.findAll(), request);
        return "redirect:/userAdmin";
    }
}
