package stu.najah.se.core.service;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import stu.najah.se.core.EmailException;

import java.util.Properties;

final class EmailUtil {

    private EmailUtil() {
    }

    public static void sendEmail(String to, String subject, String body) throws EmailException {
        var props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        final var from = System.getenv("JAVAMAIL_USER");
        final var password = System.getenv("JAVAMAIL_PASS");

        var session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            var message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new EmailException(e);
        }
    }
}
