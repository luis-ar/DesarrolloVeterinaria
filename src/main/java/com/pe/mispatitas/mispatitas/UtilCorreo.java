package com.pe.mispatitas.mispatitas;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.io.InputStream;
import java.util.Properties;

public class UtilCorreo {

    public static void main(String[] args) {
        // TODO code application logic here

        Properties proo = new Properties();
        try (InputStream input = UtilCorreo.class.getClassLoader().getResourceAsStream("config.properties")) {

            if (input == null) {
                System.err.println("Sorry, error config.properties");
                return;
            }
            proo.load(input);

        } catch (Exception e) {
            e.printStackTrace();
        }

        final String username = proo.getProperty("email");
        final String password = proo.getProperty("password");

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.user", "luissantos12@gmail.com");
        props.put("mail.smtp.starttls.enable", "true");

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };

// Configura la autenticación en tu sesión de JavaMail
        Session session = Session.getInstance(props, authenticator);
        try {
            Message message = new MimeMessage(session);
            // Establecer la dirección de correo electrónico del remitente
            message.setFrom(new InternetAddress("correo_remitente@gmail.com"));

// Establecer la dirección de correo electrónico del destinatario
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("luisdemaryori@gmail.com"));
            message.setSubject("Test");
            message.setText("helllllllllllo");
            Transport.send(message);

            System.err.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
