package it.quix.academy.qborrow.mail;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class MailServiceCustom {

    public MailServiceCustom() {
    }

    private static Log log = LogFactory.getLog(MailServiceCustom.class);

    private String destinatario;

    private String soggetto;

    private String messaggio;

    private String mittente;

    private MailSender mailSenderCustom;

    public void init() {
        log.info("MailServiceCustom initialized");
    }

    public void sendEmail(String destinatario, String soggetto, String messaggio) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(this.mittente);
            simpleMailMessage.setSubject(soggetto);
            simpleMailMessage.setText(messaggio);
            simpleMailMessage.setTo(destinatario);
            mailSenderCustom.send(simpleMailMessage);
        } catch (MailException e) {
            // TODO Auto-generated catch block
            log.error("Questo è un errore", e);
        }
    }

    /**
     * @return the destinatario
     */
    public String getDestinatario() {
        return destinatario;
    }

    /**
     * @param destinatario the destinatario to set
     */
    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    /**
     * @return the soggetto
     */
    public String getSoggetto() {
        return soggetto;
    }

    /**
     * @param soggetto the soggetto to set
     */
    public void setSoggetto(String soggetto) {
        this.soggetto = soggetto;
    }

    /**
     * @return the messaggio
     */
    public String getMessaggio() {
        return messaggio;
    }

    /**
     * @param messaggio the messaggio to set
     */
    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    /**
     * @return the mittente
     */
    public String getMittente() {
        return mittente;
    }

    /**
     * @param mittente the mittente to set
     */
    public void setMittente(String mittente) {
        this.mittente = mittente;
    }

    /**
     * @return the mailSenderCustom
     */
    public MailSender getMailSenderCustom() {
        return mailSenderCustom;
    }

    /**
     * @param mailSenderCustom the mailSenderCustom to set
     */
    public void setMailSenderCustom(MailSender mailSenderCustom) {
        this.mailSenderCustom = mailSenderCustom;
    }

}
