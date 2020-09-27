package io.kdcoder.coronavirustracker.services;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.kdcoder.coronavirustracker.models.CovidData;

@Service
public class CovidTrackerService {
	@Autowired
	private RestTemplate restTemplate;
	
	private static final String URL = "https://api.data.gov.in/resource/cd08e47b-bd70-4efb-8ebc-589344934531?limit=all&api-key=579b464db66ec23bdd000001cdc3b564546246a772a26393094f5645&format=viz";
	private CovidData covidData;
	
	public CovidData getAllCovidData() {
		covidData = new CovidData();
		String data = restTemplate.getForObject(URL, String.class);
		JSONObject json = new JSONObject(data);
		JSONArray dataArray = (JSONArray) json.get("data");
		int totalCase = 0;
		int recovered = 0;
		int death = 0;
		for(int i =0;i<dataArray.length();i++) {
			JSONArray arr = (JSONArray) dataArray.get(i);
			totalCase += Integer.parseInt((String)arr.get(2));
			recovered += Integer.parseInt((String)arr.get(3));
			death += Integer.parseInt((String)arr.get(4));
		}
		covidData.setTotalCase(totalCase+"");
		covidData.setTotalRecoverd(recovered+" ");
		covidData.setTotalDeath(death+" ");
		return covidData;
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

}
