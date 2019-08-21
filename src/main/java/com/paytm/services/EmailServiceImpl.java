package com.paytm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private JavaMailSenderImpl mailSender;

    public EmailServiceImpl() {
        mailSender = new JavaMailSenderImpl();
    }

    @Async
    @Override
    public void sendEmail(SimpleMailMessage email) {
       mailSender.send(email);
    }
}
