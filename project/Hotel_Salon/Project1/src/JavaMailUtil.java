/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author praja
 */

import java.util.Properties;
import java.util.logging.Level;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class JavaMailUtil {
 public static void sendMail(String recepient,String otp) throws Exception {

  String host="smtp.gmail.com";
  final String user="xxxxxx@gmail.com";//sender mail id 
  final String password="xxxxxx";//sender mail id password

   System.out.println("Preparing to Send Email");
   //Get the session object
   Properties props = new Properties();
   props.put("mail.smtp.auth", "true");
   props.put("mail.smtp.starttls.enable","true");
   props.put("mail.smtp.host",host);
   props.put("mail.smtp.port","587");
   
   
   Session session = Session.getInstance(props,new Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
	return new PasswordAuthentication(user,password);
      }
    });
   
   Message message = prepareMessage(session,user,recepient,otp);
   
     try {
         Transport.send(message);
     } catch (MessagingException ex) {
         java.util.logging.Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
     }
   System.out.println("Message send successfully");
 }
 
 private static Message prepareMessage(Session session,String user,String recepient,String otp){
   //Compose the message
    try {
     Message message = new MimeMessage(session);
     message.setFrom(new InternetAddress(user));
     message.setRecipient(Message.RecipientType.TO,new InternetAddress(recepient));
     message.setSubject("OTP for Changing Password");
     String htmlCode = "<h2>This is System Generated Mail. Don't Reply</h2><br> <h1><b><font color=\"red\">OTP : "+otp+" </font></b?</h1>";
     message.setContent(htmlCode,"text/html");
//     message.setText("This is System Generated Mail. Don't Reply\n OTP :=");
     return message;  
     } catch (MessagingException ex) {
        java.util.logging.Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
     }
    return null;
 }
}
