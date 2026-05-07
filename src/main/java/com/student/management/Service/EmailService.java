package com.student.management.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendPasswordToEmail(String toEmail, String password, String name)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Welcome to Newgen 🎓");

        message.setText(
                "Dear "+name+",\n\n" +

                        "Welcome to the Student Management System!\n\n" +

                        "Your registration has been successfully completed. Below are your login details:\n\n" +

                        "Username: " + toEmail + "\n" +
                        "Password: " + password + "\n\n" +

                        "🔒 For security reasons, please log in and change your password immediately.\n\n" +

                        "You can access your account here:\n" +
                        "http://localhost:5173/login\n\n" +

                        "If you did not request this registration, please contact support immediately.\n\n" +

                        "Best regards,\n" +
                        "Newgen Student Management Team"
        );

        mailSender.send(message);
    }
}
