package com.aireplye.aiwriter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String toEmail, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        // message.setFrom("");
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
        System.out.println("mail send successfully");
        log.info("message send");
    }

    // html email sender 
    public void sendHtmlMail(String toEmail, String subject, String html) throws MessagingException{
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(html, true);
        javaMailSender.send(message);
    }
}
