package fr.atlantique.imt.inf211.jobmngt.controller;

import fr.atlantique.imt.inf211.jobmngt.entity.Application;
import fr.atlantique.imt.inf211.jobmngt.entity.Field;
import fr.atlantique.imt.inf211.jobmngt.entity.JobOffer;
import fr.atlantique.imt.inf211.jobmngt.service.ApplicationService;
import fr.atlantique.imt.inf211.jobmngt.service.FieldService;
import fr.atlantique.imt.inf211.jobmngt.service.JobOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/applications")
public class ApplicationController {

    @Autowired
    ApplicationService aServ;

    @Autowired
    FieldService fServ;

    @Autowired
    JobOfferService jServ;

    @RequestMapping("/panel")
    public ModelAndView applicationPanel() {
        ModelAndView mav = new ModelAndView("application/applicationPanel");
        mav.addObject("nb_applications", aServ.countOfApplications());
        return mav;
    }

    @RequestMapping("")
    public ModelAndView listOfApplications() {
        ModelAndView mav = new ModelAndView("application/applicationList");
        mav.addObject("applicationslist", aServ.listOfApplications());
        return mav;
    }

    @RequestMapping("/{id}")
    public ModelAndView applicationDetails(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("application/applicationView");
        Application a = aServ.findById(id);
        List<JobOffer> offerslist = jServ.findByFieldsAndQualif(a.getFields(), a.getQualificationlevel());
        mav.addObject("app", a);
        mav.addObject("fieldslist", fServ.listOfSectors());
        mav.addObject("offerslist", offerslist);
        return mav;
    }

}