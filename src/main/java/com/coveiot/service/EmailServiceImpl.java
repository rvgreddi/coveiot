package com.coveiot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.coveiot.persistense.model.Person;
import com.coveiot.sendmail.MessagePreparator;

/**
 * 
 * @author VENUGOPAL
 *
 */
@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	JavaMailSender mailSender;

	@Override
	public void sendEmail(Person user, String loginURL) {
		MimeMessagePreparator preparator = MessagePreparator.getMessagePreparator(user, loginURL);
		try {
			mailSender.send(preparator);
		} catch (MailException ex) {
			System.err.println(ex.getMessage());
		}
	}

}
