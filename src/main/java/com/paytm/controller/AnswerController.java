package com.paytm.controller;

import com.paytm.configuration.DBConfiguration;
import com.paytm.entity.Answer;
import com.paytm.entity.Question;
import com.paytm.entity.User;
import com.paytm.services.AnswerServiceImpl;
import com.paytm.services.LoginServiceImpl;
import com.paytm.services.QuestionServiceImpl;
import com.paytm.services.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/*
 * @author: aditya10.kumar
 * @created: 06/08/19
 */
@Controller
public class AnswerController {
    private static final Logger LOG = LoggerFactory.getLogger(DBConfiguration.class);

    @Autowired
    private AnswerServiceImpl answerService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private QuestionServiceImpl questionService;

    @Autowired
    private LoginServiceImpl ls;

    @RequestMapping(value = "/answer", method = RequestMethod.POST)
    public ModelAndView answerGiven(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("Inside add answer method");
        String answer = request.getParameter("answer");
        LOG.info(answer);

        int q_id = Integer.parseInt(request.getParameter("ques"));


        Question ques = questionService.getQuestionByQuestionIdService(q_id);

        String email = (String) request.getSession().getAttribute("email");
        LOG.info(email);

        User u = userService.findUserByEmailService(email);

        Answer ans = new Answer();
        ans.setAnswer(answer);
        ans.setUser(u);
        ans.setQuestion(ques);
        answerService.saveAnswerService(ans);

        LOG.info("here in add Answer");
        List<Answer> Alist = answerService.findAllAnswerByQuestionService(ques);

        LOG.info(u.getU_name());
        LOG.info(ques.getQuestion_Id().toString());
        LOG.info(ques.getQuestion());

        Date createdDate = ques.getCreated();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String askDate = formatter.format(createdDate);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("showAnswers.jsp");
        mv.addObject("user", ques.getUser().getU_name());
        mv.addObject("askDate", askDate);
        mv.addObject("ques_id", ques.getQuestion_Id());
        mv.addObject("ques", ques.getQuestion());
        mv.addObject("Alist", Alist);
        return mv;
    }

    @RequestMapping(value = "/answer", method = RequestMethod.DELETE)
    public void deleteAnswer(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("answer_id"));
        answerService.deleteAnswerByAnswerIdService(id);
    }

    @RequestMapping(value = "/answer", method = RequestMethod.PUT)
    public void updateAnswer(HttpServletRequest request, HttpServletResponse response) {
        int ans_id = Integer.parseInt(request.getParameter("answer_id"));
        String answer = request.getParameter("answer");
        answerService.updateAnswerByAnswerIdService(ans_id, answer);
    }

    @RequestMapping(value = "/answer", method = RequestMethod.GET)
    public ModelAndView showAnswer(HttpServletRequest request, HttpServletResponse response) {
        Question q = (Question) request.getAttribute("ques");

        LOG.info("Here in showAnswer-----------1");
        //retrieve user & user_id using ques id
        int id = q.getQuestion_Id();

        User u = q.getUser();
        LOG.info("id is " + id);

        LOG.info(u.getU_name());

        LOG.info("Here in showAnswer-----------2");

        //retrieve all answers from question id
        List<Answer> Alist = answerService.findAllAnswerByQuestionService(q);

        Date createdDate = q.getCreated();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String askDate = formatter.format(createdDate);
        //LOG.info("Qwerty"+request.getContextPath());
        LOG.info(u.getU_name());

        ModelAndView mv = new ModelAndView();
        mv.setViewName("showAnswers.jsp");
        mv.addObject("user", u.getU_name());
        mv.addObject("askDate", askDate);
        mv.addObject("ques_id", id);
        mv.addObject("ques", q.getQuestion());
        mv.addObject("Alist", Alist);
        return mv;
    }

    @RequestMapping(value = "/showAllAnswer", method = RequestMethod.POST)
    public ModelAndView showAllAnswer(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        User u = ls.findUserByEmailService(email);
        List<Answer> listAnswer = answerService.findAllAnswerByUserService(u);
        Iterator<Answer> it = listAnswer.iterator();
        while (it.hasNext()) {
            LOG.info(it.next().getAnswer());
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("showAllAnswerByUser.jsp");
        mv.addObject("answer", listAnswer);
        LOG.info("here is show all answer by user................");
        return mv;
    }
}