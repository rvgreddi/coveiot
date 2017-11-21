package com.coveiot.service;

import com.coveiot.persistense.model.Person;
/**
 * 
 * @author VENUGOPAL
 *
 */
public interface EmailService {

	public void sendEmail(Person user, String url);

}
