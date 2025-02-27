package fr.atlantique.imt.inf211.jobmngt.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CompanyController {
    

    @RequestMapping("/companypanel")
	public ModelAndView companyPanel(){
        ModelAndView mav = new ModelAndView("companypanel");
        mav.addObject("nb_companies", 0);
        return mav;
	}

    
}
