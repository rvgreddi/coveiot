package com.coveiot.service;

import java.util.List;
import java.util.Optional;

/**
 * 
 * @author VENUGOPAL
 *
 */

import com.coveiot.persistense.model.Person;

/**
 * 
 * @author VENUGOPAL
 *
 */

public interface PersonService {

	public Person createPerson(Person user);

	public List<Person> getPersonDetails();

	public Optional<Person> finPersonById(Long id);

	public Person update(Person user);
}
