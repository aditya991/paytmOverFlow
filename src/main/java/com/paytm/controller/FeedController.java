package com.paytm.controller;

import com.paytm.dal.DeptDalImpl;
import com.paytm.entity.Dept;
import com.paytm.entity.User;
import com.paytm.services.InterestServiceImpl;
import com.paytm.services.UserServiceImpl;
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

    @RequestMapping(value = "/addinterest", method = RequestMethod.GET)
    public ModelAndView addInterest(HttpServletRequest req, HttpServletResponse res){

        HttpSession session = req.getSession(false);
        UserServiceImpl us = new UserServiceImpl();
        InterestServiceImpl is = new InterestServiceImpl();
        DeptDalImpl ds = new DeptDalImpl();
        ModelAndView mv = new ModelAndView();

        String deptName=req.getParameter("deptName");
        String email = (String) session.getAttribute("email");
        User u = us.findUserByEmail(email);
        Dept d = ds.findDeptByName(deptName);
        if(is.addInterest(u,d)){
            System.out.println("Interest successfully added.");
        }
        else{
            System.out.println("Error in adding Interest.");
        }
        return mv;
    }

    @RequestMapping(value = "/removeinterest", method = RequestMethod.GET)
    public ModelAndView removeInterest(HttpServletRequest req, HttpServletResponse res){
        HttpSession session = req.getSession(false);
        UserServiceImpl us = new UserServiceImpl();
        InterestServiceImpl is = new InterestServiceImpl();
        DeptDalImpl ds = new DeptDalImpl();
        ModelAndView mv = new ModelAndView();

        String deptName=req.getParameter("deptName");
        String email = (String) session.getAttribute("email");
        User u = us.findUserByEmail(email);
        Dept d = ds.findDeptByName(deptName);
        if(is.removeInterest(u,d)){
            System.out.println("Interest successfully removed.");
        }
        else{
            System.out.println("Error in removing Interest.");
        }
        return mv;
    }

    //@RequestMapping(value = "/showallinterest", method = RequestMethod.POST)
    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public ModelAndView  showAllInterest(HttpServletRequest req, HttpServletResponse res) {
        HttpSession session = req.getSession(false);
        UserServiceImpl us = new UserServiceImpl();
        InterestServiceImpl is = new InterestServiceImpl();
        ModelAndView mv = new ModelAndView();

        String email = (String) session.getAttribute("email");
        User u = us.findUserByEmail(email);

        List<String> resultSet = is.showAllInterest(u);

        mv.setViewName("Profile.jsp");
        mv.addObject("listofinterest",resultSet);

        return  mv;
    }

    /*@RequestMapping(value = "/profile", method = RequestMethod.POST)
    public ModelAndView showAllDepartment(HttpServletRequest req, HttpServletResponse res){
        HttpSession session = req.getSession(false);
        String email=session.getAttribute("");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Profile.jsp");
        mv.addObject("",);
        return  mv;
    }*/
}