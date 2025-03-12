package fr.atlantique.imt.inf211.jobmngt.controller;

import fr.atlantique.imt.inf211.jobmngt.entity.*;
import fr.atlantique.imt.inf211.jobmngt.service.*;
import jakarta.servlet.http.HttpServletRequest;
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
@RequestMapping("/applications")
public class ApplicationController {

    @Autowired
    ApplicationService aServ;

    @Autowired
    FieldService fServ;

    @Autowired
    JobOfferService jServ;

    @Autowired
    CandidateService cServ;

    @Autowired
    QualificationLevelService qServ;

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

    @RequestMapping("/add")
    public ModelAndView addApplication() {
        ModelAndView mav = new ModelAndView("application/applicationAddForm");
        mav.addObject("fieldslist", fServ.listOfSectors());
        return mav;
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public String addApplication(@RequestParam String cv, @RequestParam String qualif, @RequestParam String selectedFields, HttpSession request) {
        Application a = new Application();
        Candidate c = cServ.getCandidate((String) request.getAttribute("mail"));
        QualificationLevel q = qServ.findQualificationLevel(qualif);
        Set<Field> f = fServ.findFields(selectedFields);
        a.setCandidate(c);
        a.setCv(cv);
        a.setQualificationlevel(q);
        a.setFields(f);
        a.setAppdate(Date.from(java.time.Instant.now()));
        aServ.addApplication(a);
        return "redirect:/applications/" + a.getId();
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView editApplication(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("application/applicationEditForm");
        Application a = aServ.findById(id);
        mav.addObject("app", a);
        mav.addObject("fieldslist", fServ.listOfSectors());
        return mav;
    }

    @RequestMapping(value="/edit/{id}", method = RequestMethod.POST)
    public String editApplication(@PathVariable Integer id, @RequestParam String cv, @RequestParam String qualif, @RequestParam String selectedFields) {
        Application a = aServ.findById(id);
        QualificationLevel q = qServ.findQualificationLevel(qualif);
        Set<Field> f = fServ.findFields(selectedFields);
        a.setCv(cv);
        a.setQualificationlevel(q);
        a.setFields(f);
        a.setAppdate(Date.from(java.time.Instant.now()));
        aServ.editApplication(a);
        return "redirect:/applications/" + a.getId();
    }

    @RequestMapping("/delete/{id}")
    public String deleteApplication(@PathVariable Integer id) {
        Application a = aServ.findById(id);
        aServ.deleteApplication(a);
        return "redirect:/applications";
    }

}