package com.paytm.controller;
/*
 * @author: aditya10.kumar
 * @created: 06/08/19
 */

import com.paytm.dal.DeptDalImpl;
import com.paytm.entity.Dept;
import com.paytm.entity.Question;
import com.paytm.entity.User;
import com.paytm.repo.QuestionRepo;
import com.paytm.services.InterestServiceImpl;
import com.paytm.services.QuestionServiceImpl;
import com.paytm.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private QuestionServiceImpl questionServiceImpl;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private InterestServiceImpl interestService;
    @Autowired
    private DeptDalImpl deptDal;
    @Autowired
    private QuestionRepo questionRepo;

    @Autowired
    private AnswerController answerController;

    //todo ekansh
    @RequestMapping(value = "/questionfeed", method = RequestMethod.POST)
    public ModelAndView showUserQuestionsFeed(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        ModelAndView mv = new ModelAndView();

        String email = (String) session.getAttribute("email");
        List<Question> listQuestions = questionServiceImpl.showAllQuestionService(email);

        mv.setViewName("userQuestions.jsp");
        mv.addObject("listquestions", listQuestions);
        request.getAttribute("message");
        return mv;
    }

    //todo ekansh
    @RequestMapping(value = "/manageFeed", method = RequestMethod.POST)
    public ModelAndView manageFeed(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        ModelAndView mv = new ModelAndView();

        String quesName = request.getParameter("ques");
        Question q = questionRepo.getQuestionByName(quesName);
        request.setAttribute("ques", q);
        return answerController.showAnswer(request, response);
    }

    //todo ekansh
    @RequestMapping(value = "/generalfeed", method = RequestMethod.POST)
    public ModelAndView showFeed(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        ModelAndView mv = new ModelAndView();

        String email = (String) session.getAttribute("email");
        User u = userService.findUserByEmailService(email);
        List<Dept> deptSet = interestService.getUserAllInterestService(u);

        mv.setViewName("userFeedGeneral.jsp");
        mv.addObject("listdepartments", deptSet);
        request.getAttribute("message");
        return mv;
    }

    //todo ekansh
    @RequestMapping(value = "/manageQuestion", method = RequestMethod.POST)
    public ModelAndView manageQuestion(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();

        String option = request.getParameter("option");
        String selectedQuestion = request.getParameter("selectedQuestion");
        String updatedQuestion = request.getParameter("updatedQuestion");

        if (option.equals("Update")) {
            System.out.println("Inside Update");
            System.out.println(selectedQuestion);
            System.out.println(updatedQuestion);
            questionServiceImpl.UpdateQuestionService(selectedQuestion, updatedQuestion);
            request.setAttribute("message", "Question updated successfully.");
        } else if (option.equals("Delete")) {

            if (selectedQuestion == null) {
                request.setAttribute("message", "Question not selected!");
            } else {
                questionServiceImpl.DeleteQuestionService(selectedQuestion);
                request.setAttribute("message", "Question deleted successfully.");
            }

        } else {
            request.setAttribute("message", "Invalid option on Question.");
        }

        return showUserQuestionsFeed(request, response);
    }

    //todo ekansh
    @RequestMapping(value = "/updateQuestion", method = RequestMethod.POST)
    public ModelAndView updateQuestion(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();

        System.out.println("Inside updateQuestion");

        String selectedQuestion = request.getParameter("selectedQuestion");
        String updatedQuestion = request.getParameter("updatedQuestion");

        System.out.println("Inside Update");
        System.out.println(selectedQuestion);
        System.out.println(updatedQuestion);
        questionServiceImpl.UpdateQuestionService(selectedQuestion, updatedQuestion);
        request.setAttribute("message", "Question updated successfully.");

        return showUserQuestionsFeed(request, response);
    }


    //todo ekansh
    @RequestMapping(value = "/askQuestion", method = RequestMethod.POST)
    public ModelAndView askQuestion(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();

        String email = (String) request.getSession().getAttribute("email");
        User u = userService.findUserByEmailService(email);

        List<String> resultSet = interestService.getUserAllInterestNamesService(u);
        mv.setViewName("askUserQuestion.jsp");
        mv.addObject("listofDept", resultSet);
        request.getAttribute("message");

        return mv;
    }

    //todo ekansh
    @RequestMapping(value = "/saveQuestion", method = RequestMethod.POST)
    public ModelAndView saveQuestion(HttpServletRequest request, HttpServletResponse response) {///
        String department = request.getParameter("Department");
        String question = request.getParameter("Question");

        String email = (String) request.getSession().getAttribute("email");

        ModelAndView mv = new ModelAndView();

        if (question.equals(""))
            request.setAttribute("message", "Question can't be empty!");
        else {
            if (questionServiceImpl.AddQuestionService(department, question, email))
                request.setAttribute("message", "Question successfully submitted.");
            else
                request.setAttribute("message", "Question already exists.");
        }
        return askQuestion(request, response);
    }
}
