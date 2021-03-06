package com.company;

import javax.mail.Authenticator;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Session;
import javax.mail.Transport;

public class Gmail {
    public static void sendMail(String recipient) throws Exception {
        System.out.println("Preparing to send emails!");

        Properties properties = new Properties();

        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        String myAccountEmail = "enterYourEmailHere";
        String myAccountPassword = "EnterYourPasswordHere";

        Session session = Session.getInstance(properties, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, myAccountPassword);
            }
        });

        Message message = prepareMessage(session, myAccountEmail, recipient);

        Transport.send(message);
        System.out.println("Email sent to subscriber!");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recipient){
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("New Video Uploaded!");
            message.setText("Hi subscriber, \n New video added on my channel. Please check...");
            return message;

        }catch (Exception ex){
            Logger.getLogger(Gmail.class.getName()).log(Level.SEVERE, null,ex);
        }
        return  null;


    }
}
