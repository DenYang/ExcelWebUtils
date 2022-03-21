package com.example.demo.service.impl;

import com.example.demo.config.EmailConfig;
import com.example.demo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;


@Service
public class EmailServiceImpl implements EmailService {
    private JavaMailSender javaMailSender;
    private EmailConfig config;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender, EmailConfig config) {
        this.javaMailSender = javaMailSender;
        this.config = config;
    }

    public EmailServiceImpl() {
    }

    @Override
    public void senderEmail(String from,String[] to,String subject,String text,File file) throws MessagingException {
        MimeMessage mimeMessage =  javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        helper.setSubject(subject);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSentDate(new Date());
        helper.setText(text);
        helper.addAttachment("数据测试.xls",file);
        javaMailSender.send(mimeMessage);
    }

    @Override
    public void sendSimpleMail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(config.getFrom());
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

    @Override
    public void sendFileMail(String to, String subject, String text, File file) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message,true);
            helper.setFrom(config.getFrom());
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);
            helper.addAttachment("附件.xls",file);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
