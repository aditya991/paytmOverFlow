package com.paytm.controller;
import com.paytm.repo.UserRepo;
import com.paytm.services.LoginServiceImpl;
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
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        LoginServiceImpl ls=new LoginServiceImpl();
        boolean flag=ls.UserCredentials(email, password);

<<<<<<< HEAD
<<<<<<< HEAD
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpRequest;


@Controller
public class UserController {










    @RequestMapping(value = "/signup" ,method = RequestMethod.POST )
    public ModelAndView signup(HttpServletRequest request, HttpServletResponse response)
    {

        String name= request.getParameter("name");
        String email= request.getParameter("email");
        String phone = request.getParameter("phone");
        String password= request.getParameter("password");



        
        ModelAndView mv= new ModelAndView();
        return mv;

=======
        if(flag==true)
        {
            ModelAndView mv= new ModelAndView();
            return mv;
        }
        else
        {
            ModelAndView mv= new ModelAndView();
            return mv;
        }
>>>>>>> 242b6bfd04c80fe44aedb90b3500c45ad9ec87ab
    }
}