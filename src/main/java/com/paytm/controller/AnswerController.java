package com.paytm.controller;

import com.paytm.entity.Answer;
import com.paytm.entity.Question;
import com.paytm.entity.User;
import com.paytm.services.AnswerServiceImpl;
import com.paytm.services.QuestionServiceImpl;
import com.paytm.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManagerFactory;
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

    @RequestMapping(value="/showAnswer", method= RequestMethod.POST)
    public ModelAndView showAnswer(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mv=new ModelAndView();
        String quesName = request.getParameter("selectedQuestion");
        String email= (String) request.getSession().getAttribute("email");

        Question ques = questionService.getQuestionByNameService(quesName);
        User u = userService.findUserByEmailService(email);

        String questionOwnerName = ques.getUser().getU_name();

        Date quesCreated = ques.getUpdated();
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
        String questionCreated = formatter.format(quesCreated);

        /*Answer ans=new Answer();
        ans.setAnswer(answer);
        ans.setUser(u);
        ans.setQuestion(ques);
        answerService.saveAnswerService(ans);*/

        List<Answer> answersList= answerService.findAllAnswerByQuestionService(ques);

        mv.setViewName("showAnswers.jsp");
        mv.addObject("ques_id", ques.getQuestion_Id());
        mv.addObject("ques", quesName);
        mv.addObject("Alist", answersList);
        mv.addObject("questionOwnerName",questionOwnerName);
        mv.addObject("questionCreationTime",questionCreated);
        return mv;
    }

    @RequestMapping(value="/giveAnswer", method= RequestMethod.POST)
    public ModelAndView giveAnswer(HttpServletRequest request,HttpServletResponse response){

        Answer ans = new Answer();
        String email= (String) request.getSession().getAttribute("email");

        ans.setAnswer(request.getParameter("answer"));
        ans.setUser(userService.findUserByEmailService(email));
        ans.setQuestion(questionService.getQuestionByNameService(request.getParameter("question")));
        ans.setCreated(new Date());
        ans.setUpdated(new Date());

        answerService.saveAnswerService(ans);

        return showAnswer(request,response);
    }

    @RequestMapping(value="/answer", method= RequestMethod.DELETE)
    public void deleteAnswer(HttpServletRequest request, HttpServletResponse response) {
        int id=Integer.parseInt(request.getParameter("answer_id"));
        answerService.deleteAnswerByAnswerIdService(id);
    }

    @RequestMapping(value="/answer", method= RequestMethod.PUT)
    public void updateAnswer(HttpServletRequest request, HttpServletResponse response) {
        int ans_id= Integer.parseInt(request.getParameter("answer_id"));
        String answer= request.getParameter("answer");
        answerService.updateAnswerByAnswerIdService(ans_id, answer);
    }

 /*
    @RequestMapping("/showAnswer")
    public ModelAndView showAnswer(HttpServletRequest request, HttpServletResponse response)
    {

        System.out.println("Here in showAnswer");
        //int ques_id=Integer.parseInt(request.getParameter("ques_id"));
        Question q1=new Question();
        q1.setQuestion_Id(1);
        q1.setQuestion("how to shop on paytm mall");
//        q1.setCreated(new Date());
//        q1.setUpdated(new Date());

        Question q=q1;
        //Question q= (Question) request.getAttribute("ques");
        System.out.println("Here in showAnswer------1");
        //retrieve user & user_id using ques id
        int id= q.getQuestion_Id();
        User u=questionService.getUserByQuestionIdService(id);

        //retrieve question date from question id
        //Date d=q.getCreated();
        System.out.println("Here in showAnswer-----------2");
        //retrieve all answers from question id
        List<Answer> Alist= answerService.findAllAnswerByQuestionService(q);

        System.out.println(u.getU_name());

        ModelAndView mv=new ModelAndView();
        mv.setViewName("showAnswers.jsp");
        mv.addObject("ques_id", "id");
        mv.addObject("ques", q.getQuestion());
        mv.addObject("Alist", Alist);
        return mv;
    }

 */

}