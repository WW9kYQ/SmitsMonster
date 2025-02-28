package fr.atlantique.imt.inf211.jobmngt.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;


import fr.atlantique.imt.inf211.jobmngt.service.FieldService;

@Controller
public class SectorController {

    @Autowired
    private FieldService sServ;

	@RequestMapping("/listsectors")
	public ModelAndView listOfSectors(){
        ModelAndView mav = new ModelAndView("sectorlist");
      
        mav.addObject("sectorlist", sServ.listOfSectors());
        return mav;
	}

	
	
	
}