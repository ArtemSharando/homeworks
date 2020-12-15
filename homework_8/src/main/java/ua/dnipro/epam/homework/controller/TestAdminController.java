package ua.dnipro.epam.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.dnipro.epam.homework.entity.Question;
import ua.dnipro.epam.homework.entity.Test;
import ua.dnipro.epam.homework.service.impl.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static ua.dnipro.epam.homework.manager.Links.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/testAdmin")
public class TestAdminController {

    private final SubjectServiceImpl subjectServiceImpl;
    private final ComplexityServiceImpl complexityServiceImpl;
    private final TestServiceImpl testServiceImpl;
    private final QuestionServiceImpl questionServiceImpl;
    private final AnswerServiceImpl answerServiceImpl;
    private List<Question> questions;

    @GetMapping
    public String testAdminPage(){
        return "testAdmin";
    }



    @GetMapping("createTest")
    public ModelAndView testAdminCreatePage(HttpSession session){
        ModelAndView modelAndView = new ModelAndView("createTest");
        modelAndView.addObject(SUBJECTS, subjectServiceImpl.findAll(session.getAttribute(LANG).toString()));
        modelAndView.addObject(COMPLEXITIES, complexityServiceImpl.findAll(session.getAttribute(LANG).toString()));
        return modelAndView;
    }

    @PostMapping("createTest")
    public String userAdminBan(HttpServletRequest request, HttpSession session){
        Test test = testServiceImpl.create(request.getParameter(NAME),
                Integer.parseInt(request.getParameter(TIME)),
                Integer.parseInt(request.getParameter(NUMBER_Q)),
                Long.parseLong(request.getParameter(SUBJECT)),
                Long.parseLong(request.getParameter(COMPLEXITY)));
        session.setAttribute(TEST_ID, test.getId());
        session.setAttribute(NUMBER_Q, test.getNumberOfQuestions());
        return "redirect:/testAdmin/createQuestion";
    }

    @GetMapping("createQuestion")
    public ModelAndView questionAdminCreatePage(HttpSession session){
        ModelAndView modelAndView = new ModelAndView("createQuestion");
        modelAndView.addObject(SUBJECTS, subjectServiceImpl.findAll(session.getAttribute(LANG).toString()));
        modelAndView.addObject(COMPLEXITIES, complexityServiceImpl.findAll(session.getAttribute(LANG).toString()));
        return modelAndView;
    }

    @PostMapping("createQuestion")
    public String questionAdminCreate(HttpServletRequest request, HttpSession session){
        questions = questionServiceImpl.createListOfQ(Integer.parseInt(session.getAttribute(NUMBER_Q).toString()),
                request,
                CONTENT,
                NUMBER_A,
                Long.parseLong(session.getAttribute(TEST_ID).toString()));
        session.setAttribute(QUESTIONS, questions);
        return "redirect:/testAdmin/createAnswer";
    }

    @GetMapping("createAnswer")
    public ModelAndView answerAdminCreatePage(){
        ModelAndView modelAndView = new ModelAndView("createAnswer");
        modelAndView.addObject(QUESTIONS, questions);
        return modelAndView;
    }

    @PostMapping("createAnswer")
    public String answerAdminCreate(HttpServletRequest request, HttpSession session){
        answerServiceImpl.createListOfAnswer((List<Question>) answerServiceImpl.convertObjectToList(session.getAttribute("questions")),
                request,
                Long.parseLong(session.getAttribute(TEST_ID).toString()));
        return "redirect:/home";
    }
}
