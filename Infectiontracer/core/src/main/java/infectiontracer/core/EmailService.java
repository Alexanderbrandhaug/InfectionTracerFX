package infectiontracer.core;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

/**
 * Class that sends email to different users of the InfectionTracer application. Currently, either
 * sends email to users who have forgotten their password, or sends email to close contacts of an
 * infected user.
 */
public class EmailService {
  private static final String from = "infectiontracer@gmail.com";
  private static final String pw = "Passord123";
  private static final String host = "smtp.gmail.com";

  /**
   * Method to email the close contacts of an infected user.
   *
   * @param currentUser The user that is infected.
   * @param closecontacts The close contacts of the infected user.
   */
  public void sendEmail(String currentUser, List<String> closecontacts) {
    Properties properties = System.getProperties();
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.host", host);
    properties.put("mail.smtp.user", from);
    properties.put("mail.smtp.password", pw);
    properties.put("mail.smtp.port", "587");
    properties.put("mail.smtp.auth", "true");

    for (String user : closecontacts) {
      // String to = user.getEmail();
      Session session = Session.getDefaultInstance(properties);

      try {
        // Create a default MimeMessage object.
        MimeMessage message = new MimeMessage(session);

        // Set From: header field of the header.
        message.setFrom(new InternetAddress(from));

        // Set To: header field of the header.
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(user));

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
      } catch (MessagingException mex) {
        mex.printStackTrace();
      }
    }
  }

  /**
   * Method that generates a random string to be used as a password. Helper method for
   * sendEmailWithNewPassword.
   *
   * @return A new password as String.
   */
  protected String getRandomGeneratedPassword() {
    String saltChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    StringBuilder salt = new StringBuilder();
    while (salt.length() < 18) { // length of the random string.
      int index = (int) (Math.random() * saltChars.length());
      salt.append(saltChars.charAt(index));
    }
    return salt.toString();
  }

  /**
   * Method that emails new password to user.
   *
   * @param currentUser The user who are sent a new password.
   * @return New password that is updated using InfectionTracer.
   */
  public String sendEmailWithNewPassword(String currentUser) {
    Properties properties = System.getProperties();
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.host", host);
    properties.put("mail.smtp.user", from);
    properties.put("mail.smtp.password", pw);
    properties.put("mail.smtp.port", "587");
    properties.put("mail.smtp.auth", "true");
    Session session = Session.getDefaultInstance(properties);
    String newPassword = getRandomGeneratedPassword();
    try {
      // Create a default MimeMessage object.
      MimeMessage message = new MimeMessage(session);

      // Set From: header field of the header.
      message.setFrom(new InternetAddress(from));

      // Set To: header field of the header.
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(currentUser));

      // Set Subject: header field
      message.setSubject("Password notification - Infectiontracer");

      // Now set the actual message
      message.setText("Your new password is: " + newPassword);

      // Send message
      Transport transport = session.getTransport("smtp");
      transport.connect(host, from, pw);
      transport.sendMessage(message, message.getAllRecipients());
      transport.close();
      System.out.println("Sent message successfully....");
      return newPassword;
    } catch (MessagingException mex) {
      mex.printStackTrace();
    }
    return null;
  }
}
