package ua.dnipro.epam.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.dnipro.epam.homework.service.UserService;
import ua.dnipro.epam.homework.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Controller
@RequestMapping("/userAdmin")
public class UserAdminController {

    private final UserService userService;

    @GetMapping
    public String userAdminPage(){
        return "userAdmin";
    }

    @GetMapping("ban")
    public String userAdminBanPage(HttpServletRequest request){
        request.setAttribute("users", userService.findAllActive());
        return "ban";
    }

    @PostMapping("ban")
    public String userAdminBan(HttpServletRequest request){
        userService.statusUser(request.getParameter("userId"), false);
        return "redirect:/userAdmin";
    }

    @GetMapping("unban")
    public String userAdminUnBanPage(HttpServletRequest request){
        request.setAttribute("users", userService.findAllInactive());
        return "unban";
    }

    @PostMapping("unban")
    public String userAdminUnban(HttpServletRequest request){
        userService.statusUser(request.getParameter("user"), true);
        return "redirect:/userAdmin";
    }

    @GetMapping("edit")
    public String userAdminEditPage(HttpServletRequest request){
        request.setAttribute("users", userService.findAll());
        return "editUser";
    }

    @PostMapping("edit")
    public String userAdminEdit(HttpServletRequest request){
        userService.update(userService.findAll(), request);
        return "redirect:/userAdmin";
    }
}
