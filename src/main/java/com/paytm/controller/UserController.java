package com.paytm.controller;

import com.paytm.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 * @author: aditya10.kumar
 * @created: 06/08/19
 */
@Controller
public class UserController extends HttpServlet
{
    @Autowired
    private UserRepo userRepo;

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response)
    {
        String id = request.getParameter("email");
        String password = request.getParameter("password");

        
        ModelAndView mv= new ModelAndView();
        return mv;
    }
}
