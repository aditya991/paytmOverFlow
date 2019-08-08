package com.paytm.controller;
/*
 * @author: aditya10.kumar
 * @created: 06/08/19
 */
import com.paytm.entity.Question;
import com.paytm.services.QuestionServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.paytm.services.QuestionService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class QuestionController {
    QuestionServiceImpl ques=new QuestionServiceImpl();

    @RequestMapping("/AddQuesServlet")
    public ModelAndView AddQuestion(HttpServletRequest request, HttpServletResponse response)
    {HttpSession session= (HttpSession) request.getAttribute("false");
      String department=request.getParameter("Department");
      String question=request.getParameter("Question");
        ModelAndView mvc=new ModelAndView();
       ques.AddQuestionService(department,question,session);
       mvc.setViewName("AddQuestion.jsp");
       return mvc;
    }
    @RequestMapping("/UpdateQuesServlet")
    public ModelAndView UpdateQuestion(HttpServletRequest request,HttpServletResponse response)
    { HttpSession session= (HttpSession) request.getAttribute("false");
        String Question=request.getParameter("Question");

        ModelAndView mvc=new ModelAndView();

        if(ques.ValidUser(Question_Id,session))
        {

        }
        else
        {
           mvc.setViewName("UpdateQuestion.jsp");
        }

    }
   /* @RequestMapping("/DeleteQuesServlet")
    public ModelAndView DeleteQuestion(HttpServletRequest request,HttpServletResponse response)
    {
        String department=request.getParameter("Department");
        String Question_Id=request.getParameter("Question_Id");
       /* QuestionService Ques= new QuestionService();
        QuestionService.AddQuestionService(Question)

    }*/
}

