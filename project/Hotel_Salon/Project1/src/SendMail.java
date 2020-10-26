import java.util.logging.Level;
import java.util.logging.Logger;

public class SendMail {
 public static void sendmailforreset(String id,String otp) {
     try {
         JavaMailUtil.sendMail(id,otp); //receiver mail id
     } catch (Exception ex) {
         Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
     }
 }
}
//https://myaccount.google.com/lesssecureapps