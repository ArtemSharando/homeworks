package ua.dnipro.epam.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.dnipro.epam.homework.entity.Question;
import ua.dnipro.epam.homework.entity.Test;
import ua.dnipro.epam.homework.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/testAdmin")
public class TestAdminController {

    private final SubjectService subjectService;
    private final ComplexityService complexityService;
    private final TestService testService;
    private final QuestionService questionService;
    private final AnswerService answerService;

    @GetMapping
    public String testAdminPage(){
        return "testAdmin";
    }


    @GetMapping("createTest")
    public String testAdminCreatePage(HttpServletRequest request, HttpSession session){
        request.setAttribute("subjects", subjectService.findAll(session.getAttribute("lang").toString()));
        request.setAttribute("complexities", complexityService.findAll(session.getAttribute("lang").toString()));
        return "createTest";
    }

    @PostMapping("createTest")
    public String userAdminBan(HttpServletRequest request, HttpSession session){
        Test test = testService.create(request.getParameter("name"),
                Integer.parseInt(request.getParameter("time")),
                Integer.parseInt(request.getParameter("numberQ")),
                Long.parseLong(request.getParameter("subject")),
                Long.parseLong(request.getParameter("complexity")));
        session.setAttribute("testId", test.getId());
        session.setAttribute("numberQ", test.getNumberOfQuestions());
        return "redirect:/testAdmin/createQuestion";
    }

    @GetMapping("createQuestion")
    public String questionAdminCreatePage(HttpServletRequest request, HttpSession session){
        request.setAttribute("subjects", subjectService.findAll(session.getAttribute("lang").toString()));
        request.setAttribute("complexities", complexityService.findAll(session.getAttribute("lang").toString()));
        return "createQuestion";
    }

    @PostMapping("createQuestion")
    public String questionAdminCreate(HttpServletRequest request, HttpSession session){
        List<Question>  questions = questionService.createListOfQ(Integer.parseInt(session.getAttribute("numberQ").toString()),
                request,
                "content",
                "numberA",
                Long.parseLong(session.getAttribute("testId").toString()));
        session.setAttribute("questions", questions);
        return "redirect:/testAdmin//createAnswer";
    }

    @GetMapping("createAnswer")
    public String answerAdminCreatePage(HttpServletRequest request, HttpSession session){
        request.setAttribute("questions", session.getAttribute("questions"));
        return "createAnswer";
    }

    @PostMapping("createAnswer")
    public String answerAdminCreate(HttpServletRequest request, HttpSession session){
        answerService.createListOfAnswer((List<Question>) answerService.convertObjectToList(session.getAttribute("questions")),
                request,
                Long.parseLong(session.getAttribute("testId").toString()));
        return "redirect:/home";
    }
}
