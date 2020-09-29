package units.honeycombstorage.classes;

import java.util.logging.Logger;
import javax.mail.MessagingException;

/**
 *
 * @author massi
 */
public class MailSender implements Runnable {

    private final String MITTENTE = "honeycombstorage@mail.com";
    private final String host = "https://honeycombstorage.herokuapp.com/api/consumerarea/file/";
    private String destinatario;
    private long id;
    private String filename;
    private String uploaderUsername;
    private String consumerNameSurname;

    public String getUploaderUsername() {
        return uploaderUsername;
    }

    public void setUploaderUsername(String uploaderUsername) {
        this.uploaderUsername = uploaderUsername;
    }

    public String getConsumerNameSurname() {
        return consumerNameSurname;
    }

    public void setConsumerNameSurname(String consumerUsername) {
        this.consumerNameSurname = consumerUsername;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String name) {
        this.filename = name;
    }

    public MailSender() {
        super();
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    @Override
    public void run() {
        String link = host + id + "/" + filename;
        String subject = "Uploader " + uploaderUsername + " sent new file to you!";
        String text = "Dear " + consumerNameSurname + ", the uploader " + uploaderUsername
                + " has sent the file " + filename + " to you!\nClick on the link "
                + link + " to download it!\nHere you can go to the homepage system"
                + "https://honeycombstorage.herokuapp.com/";

        try {
            MailUtility.sendMail(destinatario, MITTENTE, subject, text);
        } catch (MessagingException e) {
            Logger logger = Logger.getLogger("MAIL LOGGER");
            logger.info("Invio non riuscito!");
            logger.info(e.toString());
        }
    }

}
