package com.paytm.controller;

import com.paytm.entity.Token;
import com.paytm.entity.User;
import com.paytm.services.EmailServiceImpl;
import com.paytm.services.LoginServiceImpl;
import com.paytm.services.SignupServiceImpl;
import com.paytm.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
/*
 * @author: aditya10.kumar
 * @created: 06/08/19
 */
@Controller
public class UserController {

    @Autowired
    private SignupServiceImpl signupServiceImpl;

    @Autowired
    private LoginServiceImpl ls;

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
    public ModelAndView forgotPassword(HttpServletRequest request, HttpServletResponse response, @RequestParam("email") String userEmail) {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("loginSignup.jsp");
        // Lookup user in database by e-mail
        User foundUser = userService.findUserByEmailService(userEmail);

        if (foundUser == null) {
            request.setAttribute("message", "e-mail entered is not registered with us!");
            return mv;
        }

        request.setAttribute("message", "e-mail entered is registered with us!");

        return mv;

            /*
            if (!optional.isPresent()) {
                modelAndView.addObject("errorMessage", "We didn't find an account for that e-mail address.");
            } else {

                // Generate random 36-character string token for reset password
                User user = optional.get();
                user.setResetToken(UUID.randomUUID().toString());

                // Save token to database
                userService.saveUser(user);

                String appUrl = request.getScheme() + "://" + request.getServerName();

                // Email message
                SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
                passwordResetEmail.setFrom("support@demo.com");
                passwordResetEmail.setTo(user.getEmail());
                passwordResetEmail.setSubject("Password Reset Request");
                passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
                        + "/reset?token=" + user.getResetToken());

                emailService.sendEmail(passwordResetEmail);

                // Add success message to view
                modelAndView.addObject("successMessage", "A password reset link has been sent to " + userEmail);
            }

            modelAndView.setViewName("forgotPassword");
            return modelAndView;

        }

        // Display form to reset password
        @RequestMapping(value = "/reset", method = RequestMethod.GET)
        public ModelAndView displayResetPasswordPage(ModelAndView modelAndView, @RequestParam("token") String token) {

            Optional<User> user = userService.findUserByResetToken(token);

            if (user.isPresent()) { // Token found in DB
                modelAndView.addObject("resetToken", token);
            } else { // Token not found in DB
                modelAndView.addObject("errorMessage", "Oops!  This is an invalid password reset link.");
            }

            modelAndView.setViewName("resetPassword");
            return modelAndView;
        }

        // Process reset password form
        @RequestMapping(value = "/reset", method = RequestMethod.POST)
        public ModelAndView setNewPassword(ModelAndView modelAndView, @RequestParam Map<String, String> requestParams, RedirectAttributes redir) {

            // Find the user associated with the reset token
            Optional<User> user = userService.findUserByResetToken(requestParams.get("token"));

            // This should always be non-null but we check just in case
            if (user.isPresent()) {

                User resetUser = user.get();

                // Set new password
                resetUser.setPassword(bCryptPasswordEncoder.encode(requestParams.get("password")));

                // Set the reset token to null so it cannot be used again
                resetUser.setResetToken(null);

                // Save user
                userService.saveUser(resetUser);

                // In order to set a model attribute on a redirect, we must use
                // RedirectAttributes
                redir.addFlashAttribute("successMessage", "You have successfully reset your password.  You may now login.");

                modelAndView.setViewName("redirect:login");
                return modelAndView;

            } else {
                modelAndView.addObject("errorMessage", "Oops!  This is an invalid password reset link.");
                modelAndView.setViewName("resetPassword");
            }

            return modelAndView;
        }

        /* Going to reset page without a token redirects to login page
        @ExceptionHandler(MissingServletRequestParameterException.class)
        public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
            return new ModelAndView("redirect:login");
        }*/
    }

    @RequestMapping(value = "/indexPage", method = RequestMethod.GET)
    public ModelAndView redirectToLogin(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("hello to index servlet");

        List<String> deptList = signupServiceImpl.listAllDeptByNameService();

        for (String str : deptList)
            System.out.println("hello in UC " + str);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("loginSignup.jsp");
        mv.addObject("deptList", deptList);
        request.getAttribute("message");

        return mv;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        request.setAttribute("type", "back");
        System.out.println(request.getAttribute("type"));
        System.out.println("email is :" + request.getParameter("email"));
        try {
            boolean flag = ls.UserAuthenticationService(email, password);
            System.out.println("Flag is " + flag + ".");
            if (!flag) {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
//        System.out.println(request.getParameter("password")+"           pass is ");
        System.out.println("Inside Login Controller-----------1");

        //find the user id on the basis of his email
        User u = ls.findUserByEmailService(email);
        //then find if a active session of the user exist
        Token t1 = ls.findTokenByUserService(u);
        // if it exists then assign that token to the user otherwise generate a new one
        String modelEmail = u.getEmail();
        if (t1 != null) {
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            //session.setAttribute("password", password);
            session.setAttribute("token", t1.getToken_no());
            session.setAttribute("created", System.currentTimeMillis());
            session.setAttribute("dept", u.getDept());
            System.out.println("Used old token & logged user inside the website...");
            modelEmail = (String) session.getAttribute("email");
        } else {
            HttpSession session = request.getSession();
            UUID uuid = UUID.randomUUID();
            String randomUUIDString = uuid.toString();

            session.setAttribute("dept", u.getDept());
            session.setAttribute("email", email);
            //session.setAttribute("password", password);
            session.setAttribute("token", randomUUIDString);
            session.setAttribute("created", System.currentTimeMillis());
            //User u = ls.findUserByEmailService(email);
            Token t = new Token();
            //t.setExpiry_time();
            t.setFlag(1);
            t.setToken_no((String) session.getAttribute("token"));
            t.setUser(u);
            t.setCreated(new Date());
            t.setUpdated(new Date());

            System.out.println("Created new token & assigned to the user.");

            ls.createTokenService(t);
            modelEmail = (String) session.getAttribute("email");

            System.out.println("Passed createTokenService.");
        }
        //no caching & force reload
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setHeader("Expires", "0");

        ModelAndView mv = new ModelAndView();
        mv.setViewName("postLoggedIn.jsp");
        mv.addObject("email", modelEmail);
        return mv;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ModelAndView logout(HttpSession session) {
        System.out.println("Logging you out...session Invalidated");
        ModelAndView mv = new ModelAndView("index.jsp");

        ls.markSessionInactiveService((String) session.getAttribute("token"));   //mark that session id inactive

        session.invalidate();
        return mv;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView signup(HttpServletRequest request, HttpServletResponse response) {


        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String dept = request.getParameter("dept");


        System.out.println("step 1 in controller" + name + "  " + email + "    " + phone);


        SignupServiceImpl signupService = new SignupServiceImpl();


        ModelAndView mv = new ModelAndView();


        System.out.println(" in controller");


        boolean valid_user = signupServiceImpl.checkExistingUserService(email, phone);

        System.out.println(" in controller 1");

        if (valid_user) {
            System.out.println("User creating stage");

            boolean created = signupServiceImpl.createUserService(name, email, phone, password, dept);

            if (created) {
                mv.addObject("status", "User Created successfully");
            } else {
                mv.addObject("status", "Error in creating user");
            }
        } else {

            System.out.println("User already exists ");
            mv.addObject("status", "User already Exists");
        }

        mv.setViewName("index.jsp");
        return mv;
    }
}