package stu.najah.se.core.service;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import stu.najah.se.core.EmailException;

import java.io.IOException;
import java.util.Properties;

public final class EmailUtil {

    private EmailUtil() {
    }

    public static class Email {

        private final String subject;
        private final String body;

        public Email(String subject, String body) {
            this.subject = subject;
            this.body = body;
        }

        public String getSubject() {
            return subject;
        }

        public String getBody() {
            return body;
        }
    }

    private static final String EMAIL = System.getenv("JAVAMAIL_USER");

    private static final String PASSWORD = System.getenv("JAVAMAIL_PASS");

    private static final Properties SMTP_PROPERTIES = getProperties(); // for sending

    private static final Properties IMAP_PROPERTIES = getIMAPProperties(); // for reading

    private static Properties getProperties() {
        var props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        return props;
    }

    private static Properties getIMAPProperties() {
        var props = new Properties();
        props.put("mail.store.protocol", "imaps");
        props.put("mail.imaps.host", "imap.gmail.com");
        props.put("mail.imaps.port", "993");
        props.put("mail.imaps.ssl.enable", "true");
        return props;
    }

    public static void sendEmail(String to, String subject, String body) throws EmailException {
        var session = Session.getInstance(SMTP_PROPERTIES, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL, PASSWORD);
            }
        });

        try {
            var message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new EmailException(e);
        }
    }

    public static Email readLastSentMessage() throws EmailException {
        Session session = Session.getDefaultInstance(IMAP_PROPERTIES);
        Store store = null;
        Folder sentFolder = null;

        try {
            // Connect to the Gmail server and open the "Sent" folder
            store = session.getStore();
            store.connect(EMAIL, PASSWORD);
            // For Gmail, the sent folder is named "[Gmail]/Sent Mail"
            sentFolder = store.getFolder("[Gmail]/Sent Mail");
            sentFolder.open(Folder.READ_ONLY);

            // Retrieve the last sent message
            int messageCount = sentFolder.getMessageCount();
            var lastSentMessage = messageCount > 0 ? sentFolder.getMessage(messageCount) : null;

            if (lastSentMessage == null) {
                throw new EmailException("No messages found");
            }

            return new Email(
                    lastSentMessage.getSubject(),
                    getTextFromMessage(lastSentMessage)
            );
        } catch (MessagingException | NullPointerException | IOException e) {
            throw new EmailException(e);
        } finally {
            // Close the resources
            if (sentFolder != null) {
                try {
                    sentFolder.close(false);
                } catch (MessagingException e) {
                    // Ignore this exception
                }
            }
            if (store != null) {
                try {
                    store.close();
                } catch (MessagingException e) {
                    // Ignore this exception
                }
            }
        }
    }

    private static String getTextFromMessage(Message message)
            throws MessagingException, IOException {
        String result = "";
        if (message.isMimeType("text/plain")) {
            result = message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            result = getTextFromMimeMultipart(mimeMultipart);
        }
        return result;
    }

    private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart)
            throws MessagingException, IOException {
        String result = "";
        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result = bodyPart.getContent().toString();
                break; // We found the plain text part, no need to continue
            } else if (bodyPart.isMimeType("text/html")) {
                String html = bodyPart.getContent().toString();
                if (result.isEmpty()) {
                    result = html;
                }
            } else if (bodyPart.getContent() instanceof MimeMultipart) {
                result = getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());
            }
        }
        return result;
    }
}
