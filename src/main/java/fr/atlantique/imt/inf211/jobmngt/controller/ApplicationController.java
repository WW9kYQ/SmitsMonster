package fr.atlantique.imt.inf211.jobmngt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;


@Controller
public class ApplicationController {
    

    @RequestMapping("/applicationpanel")
	public String applicationPanel(){
      
        return "applicationpanel";
	}

}