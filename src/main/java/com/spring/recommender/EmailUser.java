package com.spring.recommender;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUser {
	
	public static void mailUser(String text,String emailId)

	{

		

		final String username = "cmpe273sjsu@gmail.com";

		final String password = "cmpe_273";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(javax.mail.Message.RecipientType.TO,
				InternetAddress.parse(emailId));
			message.setSubject("Course Recommendation");
			message.setContent(text, "text/html");
			Transport.send(message);

 

		} catch (MessagingException e) {

			throw new RuntimeException(e);

		}

	}

}
