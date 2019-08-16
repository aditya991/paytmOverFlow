package com.paytm.controller;

import com.paytm.entity.Dept;
import com.paytm.entity.User;
import com.paytm.services.*;
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
public class ProfileController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private InterestServiceImpl interestService;

    @Autowired
    private DeptServiceImpl deptService;

    @RequestMapping(value = "/addinterest", method = RequestMethod.POST)
    public ModelAndView addInterest(HttpServletRequest req, HttpServletResponse res){
        HttpSession session = req.getSession(false);

        String deptName=req.getParameter("deptName");
        String email = (String) session.getAttribute("email");

        User u = userService.findUserByEmailService(email);

        if(deptName == null) {
            req.setAttribute("message","You have already added all the Interests.");
            return showAllInterest(req,res);
        }

        Dept d = deptService.findDeptByNameService(deptName);
        if(interestService.addInterestService(u,d)){
            req.setAttribute("message","Interest successfully added.");
        }
        else{
            req.setAttribute("message","Interest can't be added.");
        }

        return showAllInterest(req,res);
    }

    @RequestMapping(value = "/removeinterest", method = RequestMethod.POST)
    public ModelAndView removeInterest(HttpServletRequest req, HttpServletResponse res){
        HttpSession session = req.getSession(false);

        String deptName=req.getParameter("deptName");
        String email = (String) session.getAttribute("email");

        User u = userService.findUserByEmailService(email);
        Dept d = deptService.findDeptByNameService(deptName);

        if(u.getDept().getDept_name().equals(d.getDept_name())) {
            req.setAttribute("message","You can't remove your own department.");
            return showAllInterest(req,res);
        }

        if(interestService.removeInterestService(u,d)){
            req.setAttribute("message","Interest successfully removed.");
        }
        else{
            req.setAttribute("message","Interest can't be removed.");
        }

        return showAllInterest(req,res);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public ModelAndView  showAllInterest(HttpServletRequest req, HttpServletResponse res) {
        HttpSession session = req.getSession(false);
        ModelAndView mv = new ModelAndView();

        String email = (String) session.getAttribute("email");
        User u= userService.findUserByEmailService(email);

        List<String> resultSet = interestService.getUserAllInterestNamesService(u);
        List<Dept> deptSet = deptService.getAllAvailableDeptService(resultSet);

        mv.setViewName("userProfile.jsp");
        mv.addObject("listofinterest",resultSet);
        mv.addObject("listofdepartments",deptSet);
        mv.addObject("username",u.getU_name());
        req.getAttribute("message");

        return  mv;
    }
}