package com.paytm.controller;

import com.paytm.dal.UserDal;
import com.paytm.entity.Answer;
import com.paytm.entity.Question;
import com.paytm.entity.User;
import com.paytm.services.AnswerServiceImpl;
import com.paytm.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/*
 * @author: aditya10.kumar
 * @created: 06/08/19
 */
@Controller
public class AnswerController
{
    @Autowired
    private EntityManagerFactory emf;

    @Autowired
    private AnswerServiceImpl answerService;

    @Autowired
    private UserServiceImpl userService;


    @RequestMapping("/answer")
    public void answerGiven(HttpServletRequest request, HttpServletResponse response)
    {
        String answer=request.getParameter("answer");
        Question ques= (Question) request.getAttribute("ques");
        String email= (String) request.getSession().getAttribute("email");
        User u = userService.findUserByEmailService(email);
        Answer ans=new Answer();
        ans.setAnswer(answer);
        ans.setUser(u);
        ans.setQuestion(ques);
        answerService.saveAnswerService(ans);
    }

    @RequestMapping("/deleteAnswer")
    public void deleteAnswer(HttpServletRequest request, HttpServletResponse response)
    {
        int ans_id= (int) request.getAttribute("answer_id");
        answerService.deleteAnswerByAnswerIdService(ans_id);
    }

    @RequestMapping("/updateAnswer")
    public void updateAnswer(HttpServletRequest request, HttpServletResponse response)
    {
        int ans_id= (int) request.getAttribute("answer_id");
        String answer= (String) request.getAttribute("answer");
        answerService.updateAnswerByAnswerIdService(ans_id, answer);
    }
}
