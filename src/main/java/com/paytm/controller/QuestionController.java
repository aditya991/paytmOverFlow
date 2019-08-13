package com.paytm.controller;
/*
 * @author: aditya10.kumar
 * @created: 06/08/19
 */
import com.paytm.entity.Dept;
import com.paytm.entity.Question;
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
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionActivationListener;
import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private QuestionServiceImpl questionServiceImpl ;
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private InterestServiceImpl interestService;

    @RequestMapping(value = "/questionfeed", method = RequestMethod.POST)
    public ModelAndView  showUserQuestionsFeed(HttpServletRequest req, HttpServletResponse res) {
        HttpSession session = req.getSession(false);
        ModelAndView mv = new ModelAndView();

        String email = (String) session.getAttribute("email");
        List <Question> listQuestions = questionServiceImpl.showAllQuestionService(email);

        mv.setViewName("userQuestions.jsp");
        mv.addObject("listquestions",listQuestions);
        req.getAttribute("message");
        return  mv;
    }

    @RequestMapping("/askQuestionServlet")
    public ModelAndView askQuestion(HttpServletRequest request,HttpServletResponse response)
    {
        ModelAndView mv = new ModelAndView();

        String email= (String) request.getSession().getAttribute("email");
        User u= userService.findUserByEmailService(email);

        List<String> resultSet = interestService.showAllInterestService(u);
        mv.setViewName("askUserQuestion.jsp");
        mv.addObject("listofDept",resultSet);
        return  mv;
    }

   /* @RequestMapping("/AddQuesServlet")
    public ModelAndView AddQuestion(HttpServletRequest request, HttpServletResponse response)
    {
        String department=request.getParameter("Department");
        String question=request.getParameter("Question");

        String email= (String) request.getSession().getAttribute("email");


        ModelAndView mvc=new ModelAndView();
        questionServiceImpl.AddQuestionService(department,question ,email);
        mvc.setViewName("Question.jsp");
        return mvc;
    }*/
    @RequestMapping("/UpdateQuesServlet")
    public ModelAndView UpdateQuestion(HttpServletRequest request,HttpServletResponse response)
    {
        String email= (String) request.getSession().getAttribute("email");
        ModelAndView mv=new ModelAndView();
        List<Question> questionList = questionServiceImpl.showAllQuestionService(email);
        mv.setViewName("updateQuestion.jsp");
        mv.addObject("listofQuestion",questionList);
        return  mv;

    }
    @RequestMapping("/DeleteQuesServlet")
    public ModelAndView DeleteQuestion(HttpServletRequest request,HttpServletResponse response)
    {
        String email= (String) request.getSession().getAttribute("email");

        ModelAndView mv=new ModelAndView();

        List<Question> questionList = questionServiceImpl.showAllQuestionService(email);


        mv.setViewName("DeleteQuestion.jsp");
        mv.addObject("listofQuestion",questionList);

        return  mv;
    }

    @RequestMapping(value="/UpdateServlet",method=RequestMethod.GET)
    public ModelAndView Update(HttpServletRequest request, HttpServletResponse response)
    {  String question=request.getParameter("question");
        String updateQuestion=request.getParameter("UpdateQuestion");
        questionServiceImpl.UpdateQuestionService(question,updateQuestion);

        ModelAndView mv=new ModelAndView();
        mv.setViewName("Question.jsp");
        return mv;

    }
    @RequestMapping(value= "/DeleteServlet",method = RequestMethod.GET)
    public ModelAndView Delete (HttpServletRequest request, HttpServletResponse response)
    {
        String question=request.getParameter("question");
        questionServiceImpl.DeleteQuestionService(question);
        ModelAndView mv=new ModelAndView();
        mv.setViewName("Question.jsp");
        return mv;

    }


}
