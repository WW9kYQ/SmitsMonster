package fr.atlantique.imt.inf211.jobmngt.controller;


import fr.atlantique.imt.inf211.jobmngt.entity.*;
import fr.atlantique.imt.inf211.jobmngt.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("/joboffers")
public class JobOfferController {
    @Autowired
    JobOfferService jServ;

    @Autowired
    FieldService fServ;

    @Autowired
    CompanyService cServ;

    @Autowired
    QualificationLevelService qServ;

    @Autowired
    ApplicationService aServ;

    @RequestMapping("/panel")
    public ModelAndView jobofferPanel() {
        ModelAndView mav = new ModelAndView("joboffer/jobPanel");
        mav.addObject("nb_joboffers", jServ.countOfJobOffers());
        return mav;
    }

    @RequestMapping("")
    public ModelAndView listOfJobOffers() {
        ModelAndView mav = new ModelAndView("joboffer/jobList");
        mav.addObject("jobofferslist", jServ.listOfJobOffers());
        return mav;
    }

    @RequestMapping("/{id}")
    public ModelAndView jobOfferDetails(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("joboffer/jobView");
        JobOffer j = jServ.findById(id);
        List<Application> applicationslist = aServ.findByFieldsAndQualif(j.getFields(), j.getQualificationlevel());
        mav.addObject("job", j);
        mav.addObject("fieldslist", fServ.listOfSectors());
        mav.addObject("applicationslist", applicationslist);
        return mav;
    }

    @RequestMapping("/add")
    public ModelAndView addJobOffer() {
        ModelAndView mav = new ModelAndView("joboffer/jobAddForm");
        mav.addObject("fieldslist", fServ.listOfSectors());
        mav.addObject("levels", qServ.listOfQualificationLevels());
        return mav;
    }
    @RequestMapping(value="/add", method = RequestMethod.POST)
    public String addJobOffer(@RequestParam String title, @RequestParam String qualif,
                              @RequestParam String selectedFields,
                              @RequestParam String desc, HttpSession request) {
        JobOffer j = new JobOffer();
        Company c = cServ.getCompany((String) request.getAttribute("mail"));
        QualificationLevel q = qServ.findQualificationLevel(qualif);
        Set<Field> f = fServ.findFields(selectedFields);
        j.setCompany(c);
        j.setTitle(title);
        j.setQualificationlevel(q);
        j.setTaskdescription(desc);
        j.setFields(f);
        j.setPublicationdate(Date.from(java.time.Instant.now()));
        jServ.addJobOffer(j);
        return "redirect:/joboffers/" + j.getId();
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView editJobOffer(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("joboffer/jobEditForm");
        JobOffer j = jServ.findById(id);
        mav.addObject("job", j);
        mav.addObject("fieldslist", fServ.listOfSectors());
        mav.addObject("levels", qServ.listOfQualificationLevels());
        return mav;
    }

    @RequestMapping(value="/edit/{id}", method = RequestMethod.POST)
    public String editJobOffer(@PathVariable Integer id, @RequestParam String title, @RequestParam String qualif, @RequestParam String selectedFields, @RequestParam String taskdescription) {
        JobOffer j = jServ.findById(id);
        QualificationLevel q = qServ.findQualificationLevel(qualif);
        Set<Field> f = fServ.findFields(selectedFields);
        j.setTitle(title);
        j.setQualificationlevel(q);
        j.setFields(f);
        j.setTaskdescription(taskdescription);
        j.setPublicationdate(Date.from(java.time.Instant.now()));
        jServ.editJobOffer(j);
        return "redirect:/joboffers/" + j.getId();
    }

    @RequestMapping("/delete/{id}")
    public String deleteJobOffer(@PathVariable Integer id) {
        JobOffer j = jServ.findById(id);
        jServ.deleteJobOffer(j);
        return "redirect:/joboffers";
    }

}

