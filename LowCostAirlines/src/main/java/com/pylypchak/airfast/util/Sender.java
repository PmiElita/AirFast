package com.pylypchak.airfast.util;
 
import java.util.Properties;
 
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class Sender {
 

    private static final String username="airfast.sup@gmail.com";
    private static final String password="ABab123456";
    private static final Properties props = new Properties();;
 
    static {
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
    }
 
    public void send(String subject, String text, String toEmail){
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
 
        try {
            MimeMessage message = new MimeMessage(session);
            //від кого
            message.setFrom(new InternetAddress(username));
            //кому
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            //Заголовок
            message.setSubject(subject);
            //Сам вміст, юзайєм setContent якщо хочемо відображати в html
            message.setContent(text,"text/html; charset=utf-8");
 
            //Відправляєм
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}