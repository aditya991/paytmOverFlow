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

    @RequestMapping(value = "/addinterest", method = RequestMethod.GET)
    public ModelAndView addInterest(HttpServletRequest req, HttpServletResponse res){
        HttpSession session = req.getSession(false);
        ModelAndView mv = new ModelAndView();

        String deptName=req.getParameter("deptName");
        String email = (String) session.getAttribute("email");

        User u = userService.findUserByEmailService(email);
        Dept d = deptDal.findDeptByNameMethod(deptName);

        mv.setViewName("Profile.jsp");

        if(interestService.addInterestService(u,d)){
            mv.addObject("message","Interest successfully added.");
        }
        else{
            mv.addObject("message","Interest can't be added.");
        }

        return mv;
    }

    @RequestMapping(value = "/removeinterest", method = RequestMethod.GET )
    public ModelAndView removeInterest(HttpServletRequest req, HttpServletResponse res){
        HttpSession session = req.getSession(false);
        ModelAndView mv = new ModelAndView();

        String deptName=req.getParameter("deptName");
        String email = (String) session.getAttribute("email");

        User u = userService.findUserByEmailService(email);
        Dept d = deptDal.findDeptByNameMethod(deptName);

        mv.setViewName("Profile.jsp");

        if(u.getDept().getDept_name().equals(d.getDept_name()))
        {
            mv.addObject("message","Interest can't be removed.");
            return mv;
        }

        if(interestService.removeInterestService(u,d)){
            mv.addObject("message","Interest successfully removed.");
        }
        else{
            mv.addObject("message","Interest can't be removed.");
        }

        return mv;
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
        mv.addObject("message","");
        return  mv;
    }

   @RequestMapping(value = "/answerfeed", method = RequestMethod.POST)
    public ModelAndView  showAnswerFeed(HttpServletRequest req, HttpServletResponse res) {
        HttpSession session = req.getSession(false);
        ModelAndView mv = new ModelAndView();

        String email = (String) session.getAttribute("email");
        User u= userService.findUserByEmailService(email);

       List<Answer> listAnswers = answerService.findAllAnswerByUserService(u);


        mv.setViewName("userAnswers.jsp");
        mv.addObject("listanswers",listAnswers);
        return  mv;
    }
}