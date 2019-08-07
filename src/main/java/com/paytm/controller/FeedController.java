package com.paytm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author: ekansh.gupta
 * @created: 06/08/19
 */

@Controller
public class FeedController {

    @RequestMapping(value = "/addinterest", method = RequestMethod.GET)
    public ModelAndView addInterest(HttpServletRequest req, HttpServletResponse res){
            ModelAndView mv = new ModelAndView();
            return  mv;
    }

    @RequestMapping(value = "/removeinterest", method = RequestMethod.GET)
    public ModelAndView removeInterest(HttpServletRequest req, HttpServletResponse res){
        ModelAndView mv = new ModelAndView();
        return  mv;
    }

    @RequestMapping(value = "/showallinterest", method = RequestMethod.POST)
    public ModelAndView showAllInterest(HttpServletRequest req, HttpServletResponse res){
        ModelAndView mv = new ModelAndView();
        return  mv;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public ModelAndView showAllDepartment(HttpServletRequest req, HttpServletResponse res){
        HttpSession session = req.getSession(false);
        String email=session.getAttribute("");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Profile.jsp");
        mv.addObject("",);
        return  mv;
    }

}