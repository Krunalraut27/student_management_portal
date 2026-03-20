package com.student.management.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendPasswordToEmail(String toEmail, String password)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Student Registration Successful");
        message.setText("Greetings,\n\nYour registration has been completed.\nYour password is: "
                + password + "\n\nThank you.");
        mailSender.send(message);
    }
}
