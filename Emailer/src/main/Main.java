package main;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import main.ui.HUD;

public class Main {

	public Main() {
		
	}
	
	public static void main(String[] args) {
		HUD ui = new HUD(800, 600);
		
	}
	
	public static void sendMail(String sender, String senderPass, String recepient, String subject, String message, boolean isHTML) {
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		
		//make security better
		String senderMail = sender;
		String senderPassword = senderPass;
		
		Session ses = Session.getInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderMail, senderPassword);
			}
		});

		Message msg;
		if(!isHTML) {
			msg = prepareMessage(ses, senderMail, recepient, subject, message);
		} else {
			msg = prepareMessageAsContent(ses, senderMail, recepient, subject, message);
		}
		try {
			Transport.send(msg);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		System.out.println("Message sent successfully !");
	}
	
	static Message prepareMessage(Session session, String senderMail, String recepient, String subject, String content) {
		try {
			var msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(senderMail));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			msg.setSubject(subject);
			msg.setText(content);
			return msg;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	static Message prepareMessageAsContent(Session session, String senderMail, String recepient, String subject, String content) {
		try {
			var msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(senderMail));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			msg.setSubject(subject);
			msg.setContent(content, "text/html");
			return msg;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
