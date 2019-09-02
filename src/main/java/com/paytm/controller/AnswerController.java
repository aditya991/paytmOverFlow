package com.paytm.controller;

import com.paytm.entity.Answer;
import com.paytm.entity.Question;
import com.paytm.entity.User;
import com.paytm.services.AnswerServiceImpl;
import com.paytm.services.QuestionServiceImpl;
import com.paytm.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/*
 * @author: aditya10.kumar
 * @created: 06/08/19
 */
@Controller
public class AnswerController
{

    @Autowired
    private AnswerServiceImpl answerService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private QuestionServiceImpl questionService;

    @RequestMapping(value="/answer", method= RequestMethod.POST)
    public ModelAndView answerGiven(HttpServletRequest request, HttpServletResponse response)
    {
        System.out.println("Inside add answer method");
        String answer=request.getParameter("answer");
        System.out.println(answer);

        int q_id= Integer.parseInt(request.getParameter("ques"));

        System.out.println(q_id);

        Question ques=questionService.getQuestionByQuestionIdService(q_id);

        String email= (String) request.getSession().getAttribute("email");
        System.out.println(email);

        User u = userService.findUserByEmailService(email);

        Answer ans=new Answer();
        ans.setAnswer(answer);
        ans.setUser(u);
        ans.setQuestion(ques);
        answerService.saveAnswerService(ans);

        System.out.println("here in add Answer");
        List<Answer> Alist= answerService.findAllAnswerByQuestionService(ques);

        System.out.println(u.getU_name());
        System.out.println(ques.getQuestion_Id());
        System.out.println(ques.getQuestion());

        Date createdDate= ques.getCreated();
        SimpleDateFormat formatter=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String askDate=formatter.format(createdDate);

        ModelAndView mv=new ModelAndView();
        mv.setViewName("showAnswers.jsp");
        mv.addObject("user", ques.getUser().getU_name());
        mv.addObject("askDate", askDate);
        mv.addObject("ques_id", ques.getQuestion_Id());
        mv.addObject("ques", ques.getQuestion());
        mv.addObject("Alist", Alist);
        return mv;
    }

    @RequestMapping(value="/answer", method= RequestMethod.DELETE)
    public void deleteAnswer(HttpServletRequest request, HttpServletResponse response)
    {
        int id=Integer.parseInt(request.getParameter("answer_id"));
        answerService.deleteAnswerByAnswerIdService(id);
    }

    @RequestMapping(value="/answer", method= RequestMethod.PUT)
    public void updateAnswer(HttpServletRequest request, HttpServletResponse response)
    {
        int ans_id= Integer.parseInt(request.getParameter("answer_id"));
        String answer= request.getParameter("answer");
        answerService.updateAnswerByAnswerIdService(ans_id, answer);
    }

    @RequestMapping(value="/answer", method= RequestMethod.GET)
    public ModelAndView showAnswer(HttpServletRequest request, HttpServletResponse response)
    {
        Question q= (Question) request.getAttribute("ques");

        System.out.println("Here in showAnswer------1");
        System.out.println(q);
        //retrieve user & user_id using ques id
        int id= q.getQuestion_Id();

        User u=q.getUser();
        System.out.println("id is "+id);

        System.out.println(u.getU_name());

        System.out.println("Here in showAnswer-----------2");

        //retrieve all answers from question id
        List<Answer> Alist= answerService.findAllAnswerByQuestionService(q);

        Date createdDate= q.getCreated();
        SimpleDateFormat formatter=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String askDate=formatter.format(createdDate);
        //System.out.println("Qwerty"+request.getContextPath());
        System.out.println(u.getU_name());

        ModelAndView mv=new ModelAndView();
        mv.setViewName("showAnswers.jsp");
        mv.addObject("user", u.getU_name());
        mv.addObject("askDate", askDate);
        mv.addObject("ques_id",id);
        mv.addObject("ques", q.getQuestion());
        mv.addObject("Alist", Alist);
        return mv;
    }
}