package com.app.Utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMailwithAttach(String to, String subject, String text, String filePath) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        // Attach the PDF file
        File file = new File(filePath);
        helper.addAttachment(file.getName(), file);

        javaMailSender.send(message);
    }
    public void sendMail(String to, String subject , String message) {
        SimpleMailMessage sm  = new SimpleMailMessage();
        sm.setTo(to);
        sm.setSubject(subject);
        sm.setText(message);
        javaMailSender.send(sm);
    }
}
