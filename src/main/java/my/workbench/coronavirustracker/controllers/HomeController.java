package my.workbench.coronavirustracker.controllers;

import my.workbench.coronavirustracker.models.LocationStats;
import my.workbench.coronavirustracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller // A spring controller is using for showing the page into an url html format.
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/") //this will take us to the root directory
    public String home(Model modelObj){
        //It will render/know the html page as we have added thymeleaf in the dependency
        //Model is imported from spring framework ui, so that we can design the html within a model
        //we need to create a modelObj for that
        List<LocationStats> allStats = coronaVirusDataService.getAllStats();
        //creating allStats object to call and get the value of AllStats using getAllStats() from the coronaVirusDataService class
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        //stream is using as a loop which will loop through and save every values
        //then map the int value of allStats using mapToInt
        //using the lambda expression stat -> for getting the value that we needed from allStats and using sum method for returning the sum of the value.
        //Lambda expression is used to calculate a small mathmetical function.

        //modelObj.addAttribute("locationStatus", coronaVirusDataService.getAllStats()); //we are setting an attribute to the controller to get the values

        modelObj.addAttribute("locationStatus", allStats);
        modelObj.addAttribute("totalReportedCases", totalReportedCases);
        return "home";
    }
}
