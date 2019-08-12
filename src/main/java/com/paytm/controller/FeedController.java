package com.paytm.controller;

import com.paytm.dal.DeptDalImpl;
import com.paytm.entity.Answer;
import com.paytm.entity.Dept;
import com.paytm.entity.User;
import com.paytm.services.AnswerServiceImpl;
import com.paytm.services.InterestServiceImpl;
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
 * @created: 06/08/19
 */

@Controller
public class FeedController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private InterestServiceImpl interestService;

    @Autowired
    private AnswerServiceImpl answerService;

    @Autowired
    private DeptDalImpl deptDal;

    @RequestMapping(value = "/addinterest", method = RequestMethod.POST)
    public ModelAndView addInterest(HttpServletRequest req, HttpServletResponse res){
        HttpSession session = req.getSession(false);

        String deptName=req.getParameter("deptName");
        String email = (String) session.getAttribute("email");

        User u = userService.findUserByEmailService(email);
        if(deptName != null) {
            Dept d = deptDal.findDeptByNameMethod(deptName);
            if(interestService.addInterestService(u,d)){
                req.setAttribute("message","Interest successfully added.");
            }
            else{
                req.setAttribute("message","Interest can't be added.");
            }
        }
        else {
            req.setAttribute("message","You have already added all the Interests.");
        }
        return showAllInterest(req,res);
    }

    @RequestMapping(value = "/removeinterest", method = RequestMethod.POST)
    public ModelAndView removeInterest(HttpServletRequest req, HttpServletResponse res){
        HttpSession session = req.getSession(false);

        String deptName=req.getParameter("deptName");
        String email = (String) session.getAttribute("email");

        User u = userService.findUserByEmailService(email);
        Dept d = deptDal.findDeptByNameMethod(deptName);

        if(u.getDept().getDept_name().equals(d.getDept_name())) {
            req.setAttribute("message","You can't remove your own department.");
        }
        else{
            if(interestService.removeInterestService(u,d)){
                req.setAttribute("message","Interest successfully removed.");
            }
            else{
                req.setAttribute("message","Interest can't be removed.");
            }
        }

        return showAllInterest(req,res);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public ModelAndView  showAllInterest(HttpServletRequest req, HttpServletResponse res) {
        HttpSession session = req.getSession(false);
        ModelAndView mv = new ModelAndView();

        String email = (String) session.getAttribute("email");
        User u= userService.findUserByEmailService(email);

        List<String> resultSet = interestService.showAllInterestService(u);
        List<Dept> deptSet = deptDal.enterAllAvailableDeptMethod(resultSet);


        mv.setViewName("Profile.jsp");
        mv.addObject("listofinterest",resultSet);
        mv.addObject("listofdepartments",deptSet);
        mv.addObject("username",u.getU_name());
        req.getAttribute("message");
       // mv.addObject("message","");
        return  mv;
    }

   /*@RequestMapping(value = "/answerfeed", method = RequestMethod.POST)
    public ModelAndView  showAnswerFeed(HttpServletRequest req, HttpServletResponse res) {
        HttpSession session = req.getSession(false);
        ModelAndView mv = new ModelAndView();

        String email = (String) session.getAttribute("email");
        User u= userService.findUserByEmailService(email);

       List<Answer> listAnswers = answerService.findAllAnswerByUserService(u);


        mv.setViewName("userAnswers.jsp");
        mv.addObject("listanswers",listAnswers);
        return  mv;
    }*/

    /*@RequestMapping(value = "/questionfeed", method = RequestMethod.POST)
    public ModelAndView  showQuestionFeed(HttpServletRequest req, HttpServletResponse res) {
        HttpSession session = req.getSession(false);
        ModelAndView mv = new ModelAndView();

        String email = (String) session.getAttribute("email");
        User u= userService.findUserByEmailService(email);

        //List<Answer> listAnswers = answerService.findAllAnswerByUserService(u);


        mv.setViewName("Question.jsp");
        //mv.addObject("listanswers",listAnswers);
        return  mv;
    }*/

    @RequestMapping(value = "/feed", method = RequestMethod.POST)
    public ModelAndView  showFeed(HttpServletRequest req, HttpServletResponse res) {
        HttpSession session = req.getSession(false);
        ModelAndView mv = new ModelAndView();

        String email = (String) session.getAttribute("email");
        User u= userService.findUserByEmailService(email);

        List<String> resultSet = interestService.showAllInterestService(u);
        //List<Answer> listAnswers = answerService.findAllAnswerByUserService(u);

        mv.setViewName("userFeed.jsp");
        mv.addObject("listofinterest",resultSet);

        return  mv;
    }

    @RequestMapping(value = "/askQuestionfeed", method = RequestMethod.POST)
    public ModelAndView  showQuestionFeed(HttpServletRequest req, HttpServletResponse res) {
        HttpSession session = req.getSession(false);
        ModelAndView mv = new ModelAndView();

        String email = (String) session.getAttribute("email");
        User u= userService.findUserByEmailService(email);

        mv.setViewName("Question.jsp");
        //mv.addObject("listanswers",listAnswers);
        return  mv;
    }

}