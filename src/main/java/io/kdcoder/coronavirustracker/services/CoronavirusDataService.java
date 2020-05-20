package io.kdcoder.coronavirustracker.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import io.kdcoder.coronavirustracker.models.LocationStats;

@Service
public class CoronavirusDataService {

	private static String DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";
	
	private List<LocationStats> allStats = new ArrayList<LocationStats>();
	
	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void fetchVirusData() throws IOException, InterruptedException {
		List<LocationStats> newStats = new ArrayList<LocationStats>();
		
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest httpRequest = HttpRequest.newBuilder()
		.uri(URI.create(DATA_URL))
		.build();
		
		HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
		
		//System.out.println(response.body());
		StringReader csvBodyReader = new StringReader(response.body());
//		Reader in = new FileReader("path/to/file.csv");
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		for (CSVRecord record : records) {
			LocationStats locationStats = new LocationStats();
			locationStats.setState(record.get("Province/State"));
			locationStats.setCountry((record.get("Country/Region")));
			String size1 = record.get(record.size()-1);
			String size2 = record.get(record.size()-2);
			if(!size1.equals("") && !size2.equals("")) {
				int latestCases = Integer.parseInt(size1);
				int prevDayCases = Integer.parseInt(size2);
				locationStats.setLatestTotal(latestCases);
				locationStats.setDiffFromPrevDay((latestCases-prevDayCases) > 0 ? latestCases-prevDayCases : 0);
			}
//		    String state = record.get("Province/State");
//		    System.out.println(locationStats);
		    newStats.add(locationStats);
//		    String customerNo = record.get("Country/Region");
//		    String name = record.get("Name");
		}
		this.allStats = newStats;
	}

	public List<LocationStats> getAllStats() {
		return allStats;
	}
	
	
}
