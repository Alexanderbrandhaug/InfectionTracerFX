package infectiontracer.core;
import java.util.*;
import java.io.*;
import jakarta.mail.*;
import jakarta.mail.event.*;
import jakarta.mail.internet.*;
import java.util.List;




public class EmailService {
    private String from = "infectiontracer@gmail.com";
    private String pw = "Passord123";

   

    String host = "smtp.gmail.com";

  public void sendEmail(String currentUser, List<User> closecontacts){
   Properties properties = System.getProperties();
   properties.put("mail.smtp.starttls.enable", "true");
   properties.put("mail.smtp.host", host);
   properties.put("mail.smtp.user", from);
   properties.put("mail.smtp.password", pw);
   properties.put("mail.smtp.port", "587");
   properties.put("mail.smtp.auth", "true");

   for(User user: closecontacts){
      String to = user.getEmail();
      Session session = Session.getDefaultInstance(properties);

      try{
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);
   
         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));
   
         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO,
                                  new InternetAddress(to));
   
         // Set Subject: header field
         message.setSubject("Warning! a closecontact is Covid-19 positive");
   
         // Now set the actual message
         message.setText(currentUser + " : has tested positive for Covid-19");
   
         // Send message
         Transport transport = session.getTransport("smtp");
         transport.connect(host, from, pw);
         transport.sendMessage(message, message.getAllRecipients());
         transport.close();
         System.out.println("Sent message successfully....");
      }catch (MessagingException mex) {
         mex.printStackTrace();
      }
   
     }
   
   }


  
}
