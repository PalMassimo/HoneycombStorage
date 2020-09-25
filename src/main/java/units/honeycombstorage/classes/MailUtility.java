package units.honeycombstorage.classes;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author massi
 */
public class MailUtility {

    public static void sendMail(String destinatario, String mittente, String subject, String text) throws MessagingException {

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.protonmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("honeycomb.storage@protonmail.com", "honeycombstorage");
            }
        });

        MimeMessage message = new MimeMessage(session);
        message.setSubject(subject);
        message.setText(text);

        // Aggiunta degli indirizzi del mittente e del destinatario
        InternetAddress fromAddress = new InternetAddress(mittente);
        InternetAddress toAddress = new InternetAddress(destinatario);
        message.setFrom(fromAddress);
        message.setRecipient(Message.RecipientType.TO, toAddress);

        // Invio del messaggio
        Transport.send(message);
    }
}
