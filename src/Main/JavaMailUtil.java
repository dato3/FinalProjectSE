package Main;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.*;
import javax.mail.internet.*;

public class JavaMailUtil {
	private static String text;
	public static void sendMail(String recepient, String txt, String method) throws MessagingException {
		text = txt;
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		String myAccountEmail = "epharmacy251@gmail.com";
		String password = "s1975734";
		
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccountEmail, password);
			}
		});
		Message message;
		if(method.equals("password")) {
			message = prepareMessageForPassword(session, myAccountEmail, recepient);			
		}
		else if(method.equals("verification")) {
			message = prepareMessageForVerification(session, myAccountEmail, recepient);
		}
		else if(method.equals("subscription")) {
			message = prepareMessageForSubscription(session, myAccountEmail, recepient);
		}
		else if(method.equals("expiration")) {
			message = prepareMessageForExpiration(session, myAccountEmail, recepient);
		}
		else {
			message = prepareMessageForVerification(session, myAccountEmail, recepient);
		}
		
		Transport.send(message);
	}
	
	private static Message prepareMessageForPassword(Session session, String myAccountEmail, String recepient) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject("Forgot Password ?");
			message.setText(text);
			return message;
		} catch (Exception ex) {
			Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
	
	private static Message prepareMessageForVerification(Session session, String myAccountEmail, String recepient) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject("Verification Code");
			message.setText(text);
			return message;
		} catch (Exception ex) {
			Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
	
	private static Message prepareMessageForSubscription(Session session, String myAccountEmail, String recepient) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject("Acknowledgement");
			message.setText(text);
			return message;
		} catch (Exception ex) {
			Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
	
	private static Message prepareMessageForExpiration(Session session, String myAccountEmail, String recepient) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject("Pass date expiration");
			message.setText(text);
			return message;
		} catch (Exception ex) {
			Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
}
