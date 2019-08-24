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
        ModelAndView mv = new ModelAndView();
        SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
        User foundUser = userService.findUserByEmailService(userEmail);

        if (foundUser == null) {
            request.setAttribute("message", "E-mail entered is not registered with us!");
        }
        else{
            foundUser.setResetToken(UUID.randomUUID().toString());
            userService.save(foundUser);

            passwordResetEmail.setTo(foundUser.getEmail());
            passwordResetEmail.setSubject("Password Reset Request");
            passwordResetEmail.setText("To reset your password, click the link below:\n" + request.getScheme() + "://" +
                    request.getServerName() + ":8080/com_paytm_war" + "/reset?token=" + foundUser.getResetToken());
            emailService.sendEmail(passwordResetEmail);

            request.setAttribute("message","You will receive a password reset link at "+foundUser.getEmail()+" in 2 minutes");
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
        User user = userService.findUserByResetTokenService(token);

        String updatedPassword = request.getParameter("updatedPassword");
        String confirmedPassword = request.getParameter("confirmedPassword");

        if(updatedPassword.equals(confirmedPassword)) {
            user.setPassword(updatedPassword);
            user.setResetToken(null);
            userService.save(user);
            mv.setViewName("index.jsp");
        }
        else{
            request.setAttribute("message","Passwords don't match.Please enter again!");
            return displayResetPasswordPage(request,response,token);
        }

        return mv;
    }
}