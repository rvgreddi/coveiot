package com.coveiot.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.coveiot.utils.CoveiotConstants;

/**
 * 
 * @author VENUGOPAL
 *
 */

@Configuration
@ComponentScan(basePackages = "com.coveiot.config")
public class CoveiotConfig {

	@Bean
	public JavaMailSender getMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(CoveiotConstants.EMAIL_HOST);
		mailSender.setPort(587);
		mailSender.setUsername(CoveiotConstants.EMAIL_ID);
		mailSender.setPassword(CoveiotConstants.EMAIL_PASSWORD);

		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.starttls.enable", "true");
		javaMailProperties.put("mail.smtp.auth", "true");
		javaMailProperties.put("mail.transport.protocol", "smtp");
		javaMailProperties.put("mail.debug", "true");

		mailSender.setJavaMailProperties(javaMailProperties);
		return mailSender;
	}

}
