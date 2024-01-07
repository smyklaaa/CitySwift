package com.example.cityswift.server.util;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class MailSender {
    public void sendMail(){
        Properties props = new Properties();
        props.put("mail.smtp.host", "localhost");
        props.put("mail.smtp.port", "1025");

        Session session = Session.getInstance(props, null);
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("test@example.com"));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("recipient@example.com", false));
            msg.setSubject("Test Mail");
            msg.setText("Hello, this is a test mail sent from Java without Spring Boot!");
            Transport.send(msg);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
