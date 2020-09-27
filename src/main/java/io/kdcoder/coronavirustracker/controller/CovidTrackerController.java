package io.kdcoder.coronavirustracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.kdcoder.coronavirustracker.models.CovidData;
import io.kdcoder.coronavirustracker.services.CovidTrackerService;

@RestController
@RequestMapping("/api/coviddata")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CovidTrackerController {
	@Autowired
	private CovidTrackerService covidTrackerService; 

	@GetMapping
	public ResponseEntity<CovidData> getAllData() {
		return ResponseEntity.status(HttpStatus.OK).body(covidTrackerService.getAllCovidData());
	}
}
