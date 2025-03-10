package fr.atlantique.imt.inf211.jobmngt.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import fr.atlantique.imt.inf211.jobmngt.service.QualificationLevelService;

@Controller
public class QualificationLevelController {

    @Autowired
    private QualificationLevelService sServ;

    @RequestMapping("/listqualificationlevels")
    public ModelAndView listOfQualificationLevels() {
        ModelAndView mav = new ModelAndView("qualificationlevellist");

        mav.addObject("qualificationlevellist", sServ.listOfQualificationLevels());
        return mav;
    }


}