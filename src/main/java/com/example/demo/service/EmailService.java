package com.example.demo.service;

import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.File;

@Service
public interface EmailService {
    void senderEmail(String from, String[] to, String subject, String text, File file) throws MessagingException;
    void sendSimpleMail(String to,String subject,String text);
    void sendFileMail(String to,String subject,String text,File file);
}
