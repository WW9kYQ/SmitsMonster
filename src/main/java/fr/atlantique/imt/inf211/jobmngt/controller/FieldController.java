package fr.atlantique.imt.inf211.jobmngt.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;


import fr.atlantique.imt.inf211.jobmngt.service.FieldService;

@Controller
public class FieldController {

    @Autowired
    private FieldService sServ;

    @RequestMapping("/listfields")
    public ModelAndView listOfSectors() {
        ModelAndView mav = new ModelAndView("fieldlist");

        mav.addObject("fieldlist", sServ.listOfSectors());
        return mav;
    }


}