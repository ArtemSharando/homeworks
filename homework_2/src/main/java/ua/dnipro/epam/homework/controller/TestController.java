package ua.dnipro.epam.homework.controller;

import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.dnipro.epam.homework.dto.QuestionContentWithAnswer;
import ua.dnipro.epam.homework.entity.Answer;
import ua.dnipro.epam.homework.entity.Test;
import ua.dnipro.epam.homework.service.impl.GradeServiceImpl;
import ua.dnipro.epam.homework.service.impl.QuestionServiceImpl;
import ua.dnipro.epam.homework.service.impl.TestServiceImpl;
import ua.dnipro.epam.homework.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/takeTest")
public class TestController {

    private final TestServiceImpl testServiceImpl;
    private final QuestionServiceImpl questionServiceImpl;
    private final UserServiceImpl userServiceImpl;
    private final GradeServiceImpl gradeServiceImpl;

    private List<QuestionContentWithAnswer> list = null;
    private String result = null;

    private final static Logger LOG = Logger.getLogger(TestController.class);

    @GetMapping
    public String testPage(HttpServletRequest request){
        request.setAttribute("list", testServiceImpl.findAllForChoose());
        return "takeTest";
    }

    @PostMapping
    public String test(HttpServletRequest request, HttpSession session){
        session.setAttribute("testId", request.getParameter("test_id"));
        return "redirect:/takeTest/test";
    }

    @GetMapping("test")
    public String testPassingPage(HttpServletRequest request, HttpSession session){
        String testStr = session.getAttribute("testId").toString();
        Long testId = Long.parseLong(testStr);

        Test test = testServiceImpl.findById(testId);

        request.setAttribute("name", test.getName());
        request.setAttribute("time", test.getTime());

        list = questionServiceImpl.findByTestId(testId);
        request.setAttribute("questionContentWithAnswers", list);

        for (QuestionContentWithAnswer questionContentWithAnswer: list){
            for(Answer answer: questionContentWithAnswer.getAnswerOfQ()){
                request.setAttribute("correctAnswer"+ answer.getId(), answer.isCorrectAnswer());
            }
        }
        return "test";
    }

    @PostMapping("test")
    public String testPassing(HttpServletRequest request, HttpSession session){
        session.setAttribute("lang", request.getParameter("lang"));
        int k = 0;
        for (QuestionContentWithAnswer questionContentWithAnswer: list){
            for(Answer answer: questionContentWithAnswer.getAnswerOfQ()){
                String correctAnswerS = request.getParameter("correctAnswer"+ answer.getId());
                if(Boolean.parseBoolean(correctAnswerS)){
                    k++;
                }
            }
        }

        String result = questionServiceImpl.percent(list.size(),k);
        session.setAttribute("result", result);
        return "redirect:/takeTest/completedTest";
    }

    @GetMapping("completedTest")
    public String testCompletedPage(HttpSession session){
        result = session.getAttribute("result").toString();
        return "completedTest";
    }

    @PostMapping("completedTest")
    public String testCompleted(HttpSession session){
        String username = session.getAttribute("username").toString();
        gradeServiceImpl.create(result,
                Long.parseLong(session.getAttribute("testId").toString()),
                userServiceImpl.findByUsername(username).getId());
        LOG.info(userServiceImpl.findByUsername(username).getUsername() + " passed the test " + result +"%");
        return "redirect:/home";
    }
}
