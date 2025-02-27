package fr.atlantique.imt.inf211.jobmngt.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;


@Controller
public class JobOfferController {

    @RequestMapping("/jobofferpanel")
	public String jobofferPanel(){
      
        return "jobofferpanel";
	}
}
