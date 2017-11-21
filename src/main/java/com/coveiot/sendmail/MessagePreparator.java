package com.coveiot.sendmail;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.MimeMessagePreparator;

import com.coveiot.persistense.model.Person;
import com.coveiot.utils.CoveiotConstants;

/**
 * 
 * @author VENUGOPAL
 *
 */

public class MessagePreparator {

	public static MimeMessagePreparator getMessagePreparator(final Person user, final String loginURL) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			public void prepare(MimeMessage mimeMessage) throws Exception {
				mimeMessage.setFrom(CoveiotConstants.EMAIL_ID);
				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
				mimeMessage.setText("Dear " + user.getName() + ", your login url will expire in next 15 minutes.  " + loginURL);
				mimeMessage.setSubject("Login url");
			}
		};
		return preparator;
	}
}
