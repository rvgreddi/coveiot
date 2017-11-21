package com.coveiot.service;

import org.springframework.stereotype.Service;

import com.coveiot.thirdparty.rest.RESTClient;

/**
 * 
 * @author VENUGOPAL
 *
 */
@Service
public class WeatherServiceImpl implements WeatherService {

	@Override
	public String getWeatherByZip(String zip) {
		return RESTClient.getWeatherDataByZip(zip);
	}

}
