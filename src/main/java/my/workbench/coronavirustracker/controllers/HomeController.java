package my.workbench.coronavirustracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // A spring controller is using for showing the page into a html format to a url.
public class HomeController {

    @GetMapping("/") //this will take us to the root directory
    public String home(Model modelObj){
        //It will render/know the html page as we have added thymeleaf in the dependency
        //Model is imported from spring framework ui, so that we can design the html within a model
        //we need to create a modelObj for that
        modelObj.addAttribute("testName", "Test");
        return "home";
    }
}
