package fr.atlantique.imt.inf211.jobmngt.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CandidateController {
    

    @RequestMapping("/candidatepanel")
	public ModelAndView candidatePanel(){
        ModelAndView mav = new ModelAndView("candidatepanel");
        mav.addObject("nb_candidates", 0);
        return mav;
	}

    
}
