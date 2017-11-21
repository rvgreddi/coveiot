package com.coveiot.persistense.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.coveiot.persistense.model.Person;

/**
 * 
 * @author VENUGOPAL
 *
 */

@RepositoryRestResource
public interface PersonRepository extends CrudRepository<Person, Long> {

	Person findByName(String name);

}