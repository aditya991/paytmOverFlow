package com.paytm.controller;

import com.paytm.dal.DeptDalImpl;
import com.paytm.entity.Dept;
import com.paytm.entity.User;
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
    private DeptDalImpl deptDal;

    @RequestMapping(value = "/addinterest", method = RequestMethod.GET)
    public ModelAndView addInterest(HttpServletRequest req, HttpServletResponse res){
        HttpSession session = req.getSession(false);
        ModelAndView mv = new ModelAndView();

        String deptName=req.getParameter("deptName");
        String email = (String) session.getAttribute("email");

        User u = userService.findUserByEmailService(email);
        Dept d = deptDal.findDeptByNameMethod(deptName);

        if(interestService.addInterestService(u,d)){ System.out.println("Interest successfully added."); }
        else{ System.out.println("Error in adding Interest."); }

        return mv;
    }

    @RequestMapping(value = "/removeinterest", method = RequestMethod.GET)
    public ModelAndView removeInterest(HttpServletRequest req, HttpServletResponse res){
        HttpSession session = req.getSession(false);
        ModelAndView mv = new ModelAndView();

        String deptName=req.getParameter("deptName");
        String email = (String) session.getAttribute("email");
        User u = userService.findUserByEmailService(email);
        Dept d = deptDal.findDeptByNameMethod(deptName);

        if(interestService.removeInterestService(u,d)){ System.out.println("Interest successfully removed."); }
        else{ System.out.println("Error in removing Interest."); }

        return mv;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public ModelAndView  showAllInterest(HttpServletRequest req, HttpServletResponse res) {
        HttpSession session = req.getSession(false);
        ModelAndView mv = new ModelAndView();

        String email = (String) session.getAttribute("email");
        User u= userService.findUserByEmailService(email);
        List<String> resultSet = interestService.showAllInterestService(u);

       //String userDeptName=u.getDept().getDept_name();

        List<Dept> deptSet = deptDal.enterAllAvailableDeptMethod();

        mv.setViewName("Profile.jsp");
        mv.addObject("listofinterest",resultSet);
        mv.addObject("listofdepartments",deptSet);

        return  mv;
    }
}