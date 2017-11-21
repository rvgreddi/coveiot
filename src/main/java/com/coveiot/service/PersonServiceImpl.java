package com.coveiot.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coveiot.persistense.model.Person;
import com.coveiot.persistense.repository.PersonRepository;

/**
 * 
 * @author VENUGOPAL
 *
 */

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Override
	public List<Person> getPersonDetails() {
		List<Person> persons = (List<Person>) personRepository.findAll();
		return persons;
	}

	@Override
	public Person createPerson(Person user) {
		Person person = (Person) personRepository.save(user);
		return person;
	}

	@Override
	public Optional<Person> finPersonById(Long id) {
		Optional<Person> person = Optional.empty();

		person = (Optional<Person>) personRepository.findById(id);
		return person;
	}

	@Override
	public Person update(Person user) {
		Person person = (Person) personRepository.save(user);
		return person;

	}

}
