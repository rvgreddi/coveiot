package com.coveiot.controller;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coveiot.persistense.model.Person;
import com.coveiot.service.EmailService;
import com.coveiot.service.PersonService;
import com.coveiot.utils.CoveiotConstants;
import com.coveiot.utils.StringUtils;

/**
 * 
 * @author VENUGOPAL
 *
 */

@RestController
public class PersonController {

	@Autowired
	private PersonService personService;

	@Autowired
	private EmailService emailService;

	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	public ResponseEntity<ModelMap> create(@RequestBody Person user) {
		ModelMap modelMap = new ModelMap();

		Person person = personService.createPerson(user);
		if (Objects.isNull(person)) {
			modelMap.put("exception", "User creation failed. Please contact administrator");
			modelMap.put("reasonCode", HttpStatus.EXPECTATION_FAILED.value());
			return new ResponseEntity<ModelMap>(modelMap, HttpStatus.OK);
		}

		modelMap.put("user id", person.getId());
		modelMap.put("message", "User created successfully.");
		return new ResponseEntity<ModelMap>(modelMap, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/getlogin/{id}", method = RequestMethod.GET)
	public ResponseEntity<ModelMap> create(@PathVariable(value = "id") Long id) {
		ModelMap modelMap = new ModelMap();

		Optional<Person> person = personService.finPersonById(id);
		if (Objects.isNull(person) && !person.isPresent()) {
			modelMap.put("exception", "User does not exists.");
			modelMap.put("reasonCode", HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<ModelMap>(modelMap, HttpStatus.OK);
		}
		String loginUrl = StringUtils.constructRedirectURI(person.get().getId());
		emailService.sendEmail(person.get(), loginUrl);
		modelMap.put("uri", loginUrl);
		StringBuilder builder = new StringBuilder();
		builder.append("Sent login url to registered email which will expire with in ");
		builder.append(CoveiotConstants.URI_EXPIRE_IN_MINUTES);
		builder.append(" minutes.  Alternatively user can also use ");
		builder.append(loginUrl);
		modelMap.put("message", builder.toString());
		return new ResponseEntity<ModelMap>(modelMap, HttpStatus.OK);
	}

}
