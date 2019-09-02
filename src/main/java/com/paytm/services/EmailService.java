package com.paytm.services;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;

public interface EmailService {
    @Bean
    JavaMailSender getJavaMailSender();

    @Async
    void sendEmail(SimpleMailMessage email);
}
