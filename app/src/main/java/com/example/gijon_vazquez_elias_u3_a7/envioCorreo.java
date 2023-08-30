package com.example.gijon_vazquez_elias_u3_a7;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class envioCorreo {
    public static void EnviarFormulario(String SMS){
        try {
            //Correos
            String stringSender="eliasnikolay115@gmail.com";
            String stringReceiverEmail="lazcanoblanco272@gmail.com";

            //Clave de acceso a uso del correo que envia de dos pasos con aplicaciones de terceros permitidas
            String StringPasswordSenderEmail="dyseionmtgdqeqbi";

            //Establecemos conexion al serv    idor
            String stringHost="smtp.gmail.com";
            Properties properties=System.getProperties();
            properties.put("mail.smtp.host",stringHost);
            properties.put("mail.smtp.port","465");
            properties.put("mail.smtp.ssl.enable","true");
            properties.put("mail.smtp.auth","true");

            javax.mail.Session session= Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(stringSender,StringPasswordSenderEmail);
                }
            });

            MimeMessage mimeMessage = new MimeMessage(session);

            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(stringReceiverEmail));

            //Asunto
            mimeMessage.setSubject("Actividades Complementarias");
            //Contenido
            mimeMessage.setText(SMS);

            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Transport.send(mimeMessage);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
            });

            //Envio
            thread.start();

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
