package org.jsp.bookmyticket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class AdminMailService {
	@Autowired
	private JavaMailSender javaMailSender;
	
	public String sendMail(String email, String url) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(email);
		simpleMailMessage.setText("Dear user, please activate your account "+ url);
		simpleMailMessage.setSubject("Activate your account");
		javaMailSender.send(simpleMailMessage);
		return "Registration successful";
	}
}
