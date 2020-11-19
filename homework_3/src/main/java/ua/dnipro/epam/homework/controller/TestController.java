package ua.dnipro.epam.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.dnipro.epam.homework.dto.QuestionContentWithAnswer;
import ua.dnipro.epam.homework.entity.Answer;
import ua.dnipro.epam.homework.entity.Test;
import ua.dnipro.epam.homework.service.QuestionService;
import ua.dnipro.epam.homework.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static ua.dnipro.epam.homework.manager.Links.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/takeTest")
public class TestController {

    private final TestService testService;
    private final QuestionService questionService;

    private List<QuestionContentWithAnswer> questionContentWithAnswers = null;

    @GetMapping
    public ModelAndView testPage(){
        ModelAndView modelAndView = new ModelAndView("takeTest");
        modelAndView.addObject(LIST, testService.findAllForChoose());
        return modelAndView;
    }

    @PostMapping
    public String test(HttpServletRequest request, HttpSession session){
        session.setAttribute(TEST_ID, request.getParameter(TEST_ID));
        return "redirect:/takeTest/test";
    }

    @GetMapping("test")
    public ModelAndView testPassingPage(HttpSession session){
        ModelAndView modelAndView = new ModelAndView("test");
        String testStr = session.getAttribute(TEST_ID).toString();
        Long testId = Long.parseLong(testStr);
        Test test = testService.findById(testId);
        questionContentWithAnswers = questionService.findByTestId(testId);

        for (QuestionContentWithAnswer questionContentWithAnswer: questionContentWithAnswers){
            for(Answer answer: questionContentWithAnswer.getAnswerOfQ()){
                modelAndView.addObject(CORRECT_ANSWER+ answer.getId(), answer.isCorrectAnswer());
            }
        }

        modelAndView.addObject(LIST, questionContentWithAnswers);
        modelAndView.addObject(NAME, test.getName());
        modelAndView.addObject(TIME,test.getTime());
        return modelAndView;
    }

    @PostMapping("test")
    public String testPassing(HttpServletRequest request, HttpSession session){
       testService.passingTest(request,session, questionContentWithAnswers);
        return "redirect:/home";
    }
}
