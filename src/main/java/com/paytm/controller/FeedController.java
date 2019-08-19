package com.paytm.controller;

import com.paytm.entity.Answer;
import com.paytm.entity.Dept;
import com.paytm.entity.Question;
import com.paytm.entity.User;
import com.paytm.repo.QuestionRepo;
import com.paytm.services.AnswerServiceImpl;
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

/*
 * @author: ekansh.gupta
 * @created: 16/08/19
 */

@Controller
public class FeedController {

    @Autowired
    QuestionServiceImpl questionService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    InterestServiceImpl interestService;

    @Autowired
    AnswerServiceImpl answerService;

    //todo ekansh
    @RequestMapping(value = "/questionfeed", method = RequestMethod.POST)
    public ModelAndView showUserQuestionsFeed(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        ModelAndView mv = new ModelAndView();

        String email = (String) session.getAttribute("email");
        List<Question> listQuestions = questionService.showAllQuestionService(email);

        System.out.println("Inside showUserQuestionFeed");
        mv.setViewName("userQuestions.jsp");

        mv.addObject("listquestions",listQuestions);
        request.getAttribute("message");
        return  mv;
    }

    //todo ekansh
    @RequestMapping(value = "/manageQuestion", method = RequestMethod.POST)
    public ModelAndView manageQuestion(HttpServletRequest request,HttpServletResponse response)
    {
        ModelAndView mv = new ModelAndView();

        String option=request.getParameter("option");
        String selectedQuestion=request.getParameter("selectedQuestion");
        String updatedQuestion=request.getParameter("updatedQuestion");

        if(option.equals("Update")){
            System.out.println("Inside Update");
            System.out.println(selectedQuestion);
            System.out.println(updatedQuestion);
            questionService.UpdateQuestionService(selectedQuestion,updatedQuestion);
            request.setAttribute("message","Question updated successfully.");
        }
        else if(option.equals("Delete")){

            if(selectedQuestion == null) {
                request.setAttribute("message", "Question not selected!");
            }
            else {
                questionService.DeleteQuestionService(selectedQuestion);
                request.setAttribute("message", "Question deleted successfully.");
            }

        }
        else{ request.setAttribute("message","Invalid option on Question."); }

        return  showUserQuestionsFeed(request,response);
    }

    //todo ekansh
    @RequestMapping(value = "/updateQuestion", method = RequestMethod.POST)
    public ModelAndView updateQuestion(HttpServletRequest request,HttpServletResponse response) {
        System.out.println("Inside updateQuestion");

        ModelAndView mv = new ModelAndView();

        String selectedQuestion=request.getParameter("selectedQuestion");
        String updatedQuestion=request.getParameter("updatedQuestion");

        System.out.println("Inside Update");
        System.out.println(selectedQuestion);
        System.out.println(updatedQuestion);
        questionService.UpdateQuestionService(selectedQuestion,updatedQuestion);
        request.setAttribute("message","Question updated successfully.");

        return  showUserQuestionsFeed(request,response);
    }

    //todo ekansh
    @RequestMapping(value = "/generalfeed", method = RequestMethod.POST)
    public ModelAndView  showFeed(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        ModelAndView mv = new ModelAndView();

        String email = (String) session.getAttribute("email");
        User u = userService.findUserByEmailService(email);
        String userName = u.getU_name();
        List<Dept> deptSet = interestService.getUserAllInterestService(u);

        mv.setViewName("userFeedGeneral.jsp");
        mv.addObject("listdepartments",deptSet);
        mv.addObject("userName",userName);
        request.getAttribute("message");
        return  mv;
    }

    //todo ekansh
    @RequestMapping(value = "/answerfeed", method = RequestMethod.POST)
    public ModelAndView showUserAnswersFeed(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        ModelAndView mv = new ModelAndView();

        String email = (String) session.getAttribute("email");
        List<Answer> listAnswers = answerService.findAllAnswerByUserService(userService.findUserByEmailService(email));

        mv.setViewName("userAnswers.jsp");

        mv.addObject("listanswers",listAnswers);
        request.getAttribute("message");
        return  mv;
    }

}