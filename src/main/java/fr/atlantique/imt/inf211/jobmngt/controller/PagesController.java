package fr.atlantique.imt.inf211.jobmngt.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
public class PagesController {


	@RequestMapping("/")
	public String welcomePage(){
        return "index.html";
        
	}

	
	
	
}