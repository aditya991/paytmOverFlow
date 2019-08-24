package com.paytm.controller;

import com.paytm.entity.User;
import com.paytm.services.EmailServiceImpl;
import com.paytm.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class PasswordController {

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
    public ModelAndView forgotPassword(HttpServletRequest request, HttpServletResponse response, @RequestParam("email") String userEmail) {

        System.out.println("Inside forgotPassword");
        ModelAndView mv = new ModelAndView();
        User foundUser = userService.findUserByEmailService(userEmail);

        if (foundUser == null) {
            request.setAttribute("message", "e-mail entered is not registered with us!");
        }
        else{
            System.out.println("Found User!");
            request.setAttribute("message", "e-mail entered is registered with us!");
            foundUser.setResetToken(UUID.randomUUID().toString());
            userService.save(foundUser);

            SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
            passwordResetEmail.setTo(foundUser.getEmail());
            passwordResetEmail.setSubject("Password Reset Request");
            passwordResetEmail.setText("To reset your password, click the link below:\n" + request.getScheme() + "://" +
                    request.getServerName() + ":8080/com_paytm_war" + "/reset?token=" + foundUser.getResetToken());

            emailService.sendEmail(passwordResetEmail);
            request.setAttribute("message","You will receive a password reset link at "+foundUser.getEmail()+" in 2 minutes");
            System.out.println("Email send successfully");
        }

        mv.setViewName("forgotPassword.jsp");
        return mv;
    }

    @RequestMapping(value = "/reset" ,  method= RequestMethod.GET)
    public ModelAndView displayResetPasswordPage(HttpServletRequest request,HttpServletResponse response, @RequestParam("token") String token) {
        ModelAndView mv = new ModelAndView();
        User user = userService.findUserByResetTokenService(token);

        if (user ==  null) {
            mv.setViewName("expiredResetToken.jsp");
        } else {
            mv.addObject("token", token);
            mv.setViewName("resetPassword.jsp");
        }

        return mv;
    }

    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public ModelAndView resetPassword(HttpServletRequest request,HttpServletResponse response, @RequestParam("token") String token){
        ModelAndView mv = new ModelAndView();
        System.out.println("Inside resetPassword token is " + token);
        User user = userService.findUserByResetTokenService(token);

        user.setPassword(request.getParameter("updatedPassword"));
        user.setResetToken(null);
        userService.save(user);

        mv.setViewName("index.jsp");
        return mv;
    }

    // Going to reset page without a token redirects to login page
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
        return new ModelAndView("index.jsp");
    }
}