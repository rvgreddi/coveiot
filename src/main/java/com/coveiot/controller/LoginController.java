package com.coveiot.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coveiot.persistense.model.Person;
import com.coveiot.service.PersonService;
import com.coveiot.service.WeatherService;
import com.coveiot.utils.Validator;

/**
 * 
 * @author VENUGOPAL
 *
 */

@RestController
public class LoginController {

	@Autowired
	private PersonService personService;

	@Autowired
	private WeatherService weatherService;

	@RequestMapping(value = "/user/login/{userId}", method = RequestMethod.GET)
	public ResponseEntity<ModelMap> login(@PathVariable(value = "userId") Long userId, @RequestParam(value = "tm", required = true) String dtTm) {
		ModelMap modelMap = new ModelMap();

		if (null == userId || null == dtTm) {
			modelMap.put("exception", "invalid user input. id or date is null");
			modelMap.put("reasonCode", HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<ModelMap>(modelMap, HttpStatus.OK);
		}

		Optional<Person> person = personService.finPersonById(userId);
		if (null != person && !person.isPresent()) {
			modelMap.put("exception", "User does not exists.");
			modelMap.put("reasonCode", HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<ModelMap>(modelMap, HttpStatus.OK);
		}

		LocalDateTime ldt = LocalDateTime.parse(dtTm);
		if (!Validator.dateValidate(ldt)) {
			modelMap.put("exception", "User login uri has been expired.");
			modelMap.put("reasonCode", HttpStatus.EXPECTATION_FAILED.value());
			return new ResponseEntity<ModelMap>(modelMap, HttpStatus.OK);
		}
		Person pr = person.get();
		String weather = pr.getData();
		if (null == weather || weather == "" || !Validator.weatherDateValidate(person.get().getWeatherDate())) {
			weather = weatherService.getWeatherByZip(pr.getZip());
			pr.setWeatherDate(Timestamp.valueOf(LocalDateTime.now()));
			pr.setData(weather);
			personService.update(pr);
			modelMap.put("weather", weather);
			return new ResponseEntity<ModelMap>(modelMap, HttpStatus.OK);
		}
		weather = pr.getData();
		modelMap.put("weather", weather);
		return new ResponseEntity<ModelMap>(modelMap, HttpStatus.OK);
	}

}
