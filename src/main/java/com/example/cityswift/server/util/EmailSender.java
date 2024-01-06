package com.example.cityswift.server.util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

    private final String username;
    private final String password;

    public EmailSender() {

        //username = System.getenv("EMAIL_USERNAME");
        //password = System.getenv("EMAIL_PASSWORD");
        username = "cityswiftsupport@op.pl";
        password = "LGos80ar4H";

/*        if (username == null || password == null) {
            throw new RuntimeException("Nie ustawiono zmiennych środowiskowych EMAIL_USERNAME i/lub EMAIL_PASSWORD.");
        }*/
    }

    public void sendEmail(String messageRecipientMail, String subject, String messageBody) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.poczta.onet.pl");
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(messageRecipientMail));
            message.setSubject(subject);
            message.setText(messageBody);

            Transport.send(message);

            System.out.println("Wiadomość wysłana pomyślnie.");
        } catch (MessagingException e) {
            throw new RuntimeException("Wystąpił błąd podczas wysyłania wiadomości: " + e.getMessage(), e);
        }
    }
}