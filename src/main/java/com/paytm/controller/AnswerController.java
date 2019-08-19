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
    public ModelAndView answerGiven(HttpServletRequest request, HttpServletResponse response) {

        int q_id = Integer.parseInt(request.getParameter("ques"));
        Question ques = questionService.getQuestionByQuestionIdService(q_id);

        String email = (String) request.getSession().getAttribute("email");
        User u = userService.findUserByEmailService(email);

        Answer ans = new Answer();
        ans.setAnswer(request.getParameter("answer"));
        ans.setUser(u);
        ans.setQuestion(ques);
        ans.setCreated(new Date());
        ans.setUpdated(new Date());
        answerService.saveAnswerService(ans);

        List<Answer> answersList = answerService.findAllAnswerByQuestionService(ques);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("showAnswers.jsp");
        mv.addObject("user", u.getU_name());
        mv.addObject("askDate",ques.getCreated());
        mv.addObject("ques_id", ques.getQuestion_Id());
        mv.addObject("ques", ques.getQuestion());
        mv.addObject("Alist", answersList);

        return mv;
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

    @RequestMapping(value="/answer",method= RequestMethod.GET)
    public ModelAndView showAnswer(HttpServletRequest request, HttpServletResponse response)
    {
        String quesName = request.getParameter("selectedQuestion");
        Question q = questionService.getQuestionByNameService(quesName);

        int id= q.getQuestion_Id();
        User u=questionService.getUserByQuestionIdService(id);

        Date quesCreated = q.getUpdated();
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
        String questionCreated = formatter.format(quesCreated);

        List<Answer> Alist= answerService.findAllAnswerByQuestionService(q);

        System.out.println(u.getU_name());

        ModelAndView mv=new ModelAndView();
        mv.setViewName("showAnswers.jsp");
        mv.addObject("user", u.getU_name());
        mv.addObject("ques_id", 1);
        mv.addObject("ques", q.getQuestion());
        mv.addObject("Alist", Alist);
        mv.addObject("questionCreationTime",questionCreated);
        return mv;
    }
}