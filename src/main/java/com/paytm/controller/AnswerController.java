package com.paytm.controller;

import com.paytm.dal.UserDal;
import com.paytm.entity.Answer;
import com.paytm.entity.Question;
import com.paytm.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    private UserDal userdal;

    @RequestMapping("/answer")
    public void answerGiven(HttpServletRequest request, HttpServletResponse response)
    {
        String answer=request.getParameter("answer");
        Question ques= (Question) request.getAttribute("ques");
        String email= (String) request.getSession().getAttribute("email");

        User u=userdal.findUserByEmailMethod(email);
        Answer ans=new Answer();
        ans.setAnswer(answer);
        ans.setUser(u);
        ans.setCreated(new Date());
        ans.setUpdated(new Date());
        ans.setQuestion(ques);

    }

    @RequestMapping("/deleteAnswer")
    public void deleteAnswer(HttpServletRequest request, HttpServletResponse response)
    {
        int ans_id= (int) request.getAttribute("answer_id");


    }
}
