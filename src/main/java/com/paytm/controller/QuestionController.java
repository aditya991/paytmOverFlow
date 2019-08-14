package com.paytm.controller;
/*
 * @author: aditya10.kumar
 * @created: 06/08/19
 */

import com.paytm.entity.Question;
import com.paytm.services.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.paytm.services.QuestionService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class QuestionController {


    @RequestMapping("/AddQuesServlet")
    public ModelAndView AddQuestion(HttpServletRequest request, HttpServletResponse response)
    {
        System.out.println("here in add question");
      String department=request.getParameter("Department");

      String question=request.getParameter("Question");
      ModelAndView mvc=new ModelAndView();
      QuestionServiceImpl ques=new QuestionServiceImpl();

        Integer k= ques.AddQuestionService(department,question);
        if(k==1)
       {

      return mvc;}
       else
       {
        return mvc;
       }

    }
    @RequestMapping("/UpdateQuesServlet")
    public ModelAndView UpdateQuestion(HttpServletRequest request,HttpServletResponse response)
    {
        String department=request.getParameter("Department");

        String Question_Id=request.getParameter("Question_Id");
        ModelAndView mv = new ModelAndView();
        return  mv;

    }
    @RequestMapping("/DeleteQuesServlet")
    public ModelAndView DeleteQuestion(HttpServletRequest request,HttpServletResponse response)
    {
        String department=request.getParameter("Department");

        String Question_Id=request.getParameter("Question_Id");
        ModelAndView mv = new ModelAndView();
        return  mv;
    }
}

