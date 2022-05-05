package my.workbench.coronavirustracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // A spring controller is using for showing the page into a html format to a url.
public class HomeController {

    @GetMapping("/")
    public String home(){ //It will render/know the html page as we have added thymeleaf in the dependency
        return "home";
    }
}
