package com.paytm.controller;

import com.paytm.services.LoginServiceImpl;

import com.paytm.services.SignupServiceImpl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;
/*
 * @author: aditya10.kumar
 * @created: 06/08/19
 */
@Controller

public class UserController   {





    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView signup(HttpServletRequest request, HttpServletResponse response) {


        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String dept = request.getParameter("dept");



        SignupServiceImpl signupService =new SignupServiceImpl();
        ModelAndView mv = new ModelAndView();

        boolean valid_user= signupService.validUser(email,phone);

        if(valid_user)
        {
            boolean created=signupService.createUser(name,email,phone,password,dept);

            if(created) {
                mv.addObject("status", "User Created successfully");
            }
            else {
                mv.addObject("status","Error in creating user");
            }
        }
        else {
            mv.addObject("status", "User already Exists");
        }

        mv.setViewName("index.jsp");
        return mv;
    }


}