package com.paytm.controller;

import com.paytm.entity.User;
import com.paytm.services.EmailServiceImpl;
import com.paytm.services.LoginServiceImpl;
import com.paytm.services.SignupServiceImpl;
import com.paytm.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
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


    @RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
    public ModelAndView forgotPassword(HttpServletRequest request, HttpServletResponse response, @RequestParam("email") String userEmail) {

        System.out.println("Inside forgotPassword");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("loginSignup.jsp");
        // Lookup user in database by e-mail
        User foundUser = userService.findUserByEmailService(userEmail);

        if (foundUser == null) {
            request.setAttribute("message", "e-mail entered is not registered with us!");
            return mv;
        }
        else{

            System.out.println("Found User!");
            request.setAttribute("message", "e-mail entered is registered with us!");
            foundUser.setResetToken(UUID.randomUUID().toString());
            userService.save(foundUser);
            String appUrl = request.getScheme() + "://" + request.getServerName();

            // Email message
            SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
            // passwordResetEmail.setFrom("support@demo.com");
            passwordResetEmail.setTo(foundUser.getEmail());
            passwordResetEmail.setSubject("Password Reset Request");
            passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
                    + "/reset?token=" + foundUser.getResetToken());

            emailService.sendEmail(passwordResetEmail);
            System.out.println("Email send successfully");
        }
        return mv;

    }

}
