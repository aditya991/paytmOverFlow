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
import org.apache.log4j.Logger;
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

    private static final Logger logger = Logger.getLogger(QuestionController.class);

    @Autowired
    private QuestionServiceImpl questionServiceImpl ;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private InterestServiceImpl interestService;
    @Autowired
    private QuestionRepo questionRepo;
    @Autowired
    private AnswerController answerController;

    //todo ekansh
    @RequestMapping(value = "/questionfeed", method = RequestMethod.POST)
    public ModelAndView  showUserQuestionsFeed(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Inside showUserQuestionsFeed method.");

        HttpSession session = request.getSession(false);
        ModelAndView mv = new ModelAndView();

        String email = (String) session.getAttribute("email");
        List <Question> listQuestions = questionServiceImpl.showAllQuestionService(email);

        mv.setViewName("userQuestions.jsp");
        mv.addObject("listquestions",listQuestions);
        request.getAttribute("message");
        return  mv;
    }

    //todo ekansh
    @RequestMapping(value = "/manageFeed", method = RequestMethod.POST)
    public ModelAndView  manageFeed(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Inside manageFeed method.");

        HttpSession session = request.getSession(false);
        ModelAndView mv = new ModelAndView();

        String quesName = request.getParameter("ques");
        Question q = questionRepo.getQuestionByName(quesName);
        request.setAttribute("ques",q);
        return  answerController.showAnswer(request,response);
    }

    //todo ekansh
    @RequestMapping(value = "/generalfeed", method = RequestMethod.POST)
    public ModelAndView  showFeed(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Inside showFeed method.");

        HttpSession session = request.getSession(false);
        ModelAndView mv = new ModelAndView();

        String email = (String) session.getAttribute("email");
        User u = userService.findUserByEmailService(email);
        List<Dept> deptSet = interestService.getUserAllInterestService(u);

        mv.setViewName("userFeedGeneral.jsp");
        mv.addObject("listdepartments",deptSet);
        request.getAttribute("message");
        return  mv;
    }

    //todo ekansh
    @RequestMapping(value = "/manageQuestion", method = RequestMethod.POST)
    public ModelAndView manageQuestion(HttpServletRequest request,HttpServletResponse response) {
        logger.debug("Inside manageQuestion method.");

        ModelAndView mv = new ModelAndView();

        String option=request.getParameter("option");
        String selectedQuestion=request.getParameter("selectedQuestion");
        String updatedQuestion=request.getParameter("updatedQuestion");

        if(option.equals("Update")) {
            logger.debug("Inside Update");
            logger.debug("Question selected " + selectedQuestion);
            logger.debug("Updated Question" + updatedQuestion);

            questionServiceImpl.UpdateQuestionService(selectedQuestion,updatedQuestion);
            request.setAttribute("message","Question updated successfully.");
        }
        else if(option.equals("Delete")) {
                if(selectedQuestion == null) {
                    logger.warn("Question not selected!");
                    request.setAttribute("message", "Question not selected!");
                }
                else {
                    questionServiceImpl.DeleteQuestionService(selectedQuestion);
                    logger.debug("Question deleted successfully.");
                    request.setAttribute("message", "Question deleted successfully.");
                }
        }
        else{
            logger.warn("Invalid option on Question.");
            request.setAttribute("message","Invalid option on Question.");
        }

        return  showUserQuestionsFeed(request,response);
    }

    //todo ekansh
    @RequestMapping(value = "/updateQuestion", method = RequestMethod.POST)
    public ModelAndView updateQuestion(HttpServletRequest request,HttpServletResponse response)
    {
        ModelAndView mv = new ModelAndView();

        logger.debug("Inside updateQuestion method");

        String selectedQuestion=request.getParameter("selectedQuestion");
        String updatedQuestion=request.getParameter("updatedQuestion");

        logger.debug("Inside Update");
        logger.debug("Question selected " + selectedQuestion);
        logger.debug("Updated Question " + updatedQuestion);

        questionServiceImpl.UpdateQuestionService(selectedQuestion,updatedQuestion);
        request.setAttribute("message","Question updated successfully.");

        logger.info("Question updated successfully");

        return  showUserQuestionsFeed(request,response);
    }


    //todo ekansh
    @RequestMapping(value = "/askQuestion", method = RequestMethod.POST)
    public ModelAndView askQuestion(HttpServletRequest request,HttpServletResponse response)
    {
        logger.debug("Inside askQuestion method");

        ModelAndView mv = new ModelAndView();

        String email= (String) request.getSession().getAttribute("email");
        User u= userService.findUserByEmailService(email);

        List<String> resultSet = interestService.getUserAllInterestNamesService(u);
        mv.setViewName("askUserQuestion.jsp");
        mv.addObject("listofDept",resultSet);
        request.getAttribute("message");

        logger.info("Question asked successfully");

        return  mv;
    }

    //todo ekansh
    @RequestMapping(value = "/saveQuestion", method = RequestMethod.POST)
    public ModelAndView saveQuestion(HttpServletRequest request, HttpServletResponse response)
    {
        logger.debug("Inside saveQuestion method");

        String department=request.getParameter("Department");
        String question=request.getParameter("Question");

        String email= (String) request.getSession().getAttribute("email");

        ModelAndView mv=new ModelAndView();

        if(question.equals("")) {
            logger.warn("Question can't be empty!");
            request.setAttribute("message", "Question can't be empty!");
        }
        else {
               if(questionServiceImpl.AddQuestionService(department, question, email)) {
                   logger.debug("Question successfully submitted.");
                   request.setAttribute("message", "Question successfully submitted.");
               }
               else {
                   logger.warn("Question already exists.");
                   request.setAttribute("message", "Question already exists.");
               }
             }
        logger.info("Question saved successfully");

        return askQuestion(request,response);
    }
}
