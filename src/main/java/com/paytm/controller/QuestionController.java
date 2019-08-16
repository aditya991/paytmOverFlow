package com.paytm.controller;
/*
 * @author: aditya10.kumar
 * @created: 06/08/19
 */
import com.paytm.entity.User;
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
import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private QuestionServiceImpl questionService ;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private InterestServiceImpl interestService;

    //todo ekansh
    @RequestMapping(value = "/askQuestion", method = RequestMethod.POST)
    public ModelAndView askQuestion(HttpServletRequest request,HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();

        String email= (String) request.getSession().getAttribute("email");

        User u= userService.findUserByEmailService(email);
        List<String> resultSet = interestService.getUserAllInterestNamesService(u);

        mv.setViewName("askUserQuestion.jsp");
        mv.addObject("listofDept",resultSet);
        request.getAttribute("message");

        return  mv;
    }

    //todo ekansh
    @RequestMapping(value = "/saveQuestion", method = RequestMethod.POST)
    public ModelAndView saveQuestion(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv=new ModelAndView();

        String department=request.getParameter("Department");
        String question=request.getParameter("Question");
        String email= (String) request.getSession().getAttribute("email");

        if(question.equals(""))
            request.setAttribute("message","Question can't be empty!");
        else {
               if(questionService.AddQuestionService(department, question, email))
                   request.setAttribute("message", "Question successfully submitted.");
               else
                   request.setAttribute("message", "Question already exists.");
             }

        return askQuestion(request,response);
    }
}