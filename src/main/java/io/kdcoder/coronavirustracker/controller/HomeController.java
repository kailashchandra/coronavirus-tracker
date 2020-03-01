package io.kdcoder.coronavirustracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.kdcoder.coronavirustracker.models.LocationStats;
import io.kdcoder.coronavirustracker.services.CoronavirusDataService;

@Controller
public class HomeController {

	@Autowired
	CoronavirusDataService coronavirusDataService;
	
	@GetMapping("/")
	public String home(Model model) {
		List<LocationStats> allState = coronavirusDataService.getAllStats();
		
		int totalReportedCases = allState.stream().mapToInt(stat -> stat.getLatestTotal()).sum();
		int totalNewCases = allState.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
		
		model.addAttribute("locationState",allState);
		model.addAttribute("totalReportedCases", totalReportedCases);
		model.addAttribute("totalNewCases", totalNewCases);
		
		return "home";
	}
}
