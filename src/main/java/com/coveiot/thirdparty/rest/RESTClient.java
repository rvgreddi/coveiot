package com.coveiot.thirdparty.rest;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author VENUGOPAL
 *
 */
public class RESTClient {

	private static RestTemplate getRestTemplate() {

		CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();

		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);

		RestTemplate restTemplate = new RestTemplate(requestFactory);
		return restTemplate;
	}

	public static String getWeatherDataByZip(String zip) {

		RestTemplate restTemplate = getRestTemplate();

		StringBuilder builder = new StringBuilder();
		builder.append("https://samples.openweathermap.org/data/2.5/weather?zip=");
		builder.append(84848);
		builder.append(",us&appid=b1b15e88fa797225412429c1c50c122a1");

		String weatherData = restTemplate.getForObject(builder.toString(), String.class);
		System.out.println(weatherData);
		return weatherData;
	}

}
