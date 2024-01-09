package com.example.cityswift.server.util;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class MailSender {
    public void sendMail(String email, String subject, String text, String from) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "localhost");
        props.put("mail.smtp.port", "1025");

        Session session = Session.getInstance(props, null);
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email, false));
            msg.setSubject(subject);
            msg.setText(text);
            Transport.send(msg);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
