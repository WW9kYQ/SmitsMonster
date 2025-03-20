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

import java.net.URLEncoder;
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
    public ModelAndView listOfJobOffers(@RequestParam(required = false) String error, @RequestParam(required = false) String ack) {
        ModelAndView mav = new ModelAndView("joboffer/jobList");
        mav.addObject("jobofferslist", jServ.listOfJobOffers());
        mav.addObject("error", error);
        mav.addObject("ack", ack);
        return mav;
    }

    @RequestMapping("/{id}")
    public ModelAndView jobOfferDetails(@PathVariable Integer id, @RequestParam(required = false) String ack, @RequestParam(required = false) String error) {
        ModelAndView mav = new ModelAndView("joboffer/jobView");
        JobOffer j = jServ.findById(id);
        if (j == null) {
            return new ModelAndView("redirect:/applications?error=" + URLEncoder.encode("Job offer not found", java.nio.charset.StandardCharsets.UTF_8));
        }
        List<Application> applicationslist = aServ.findByFieldsAndQualif(j.getFields(), j.getQualificationlevel());
        mav.addObject("job", j);
        mav.addObject("fieldslist", fServ.listOfSectors());
        mav.addObject("applicationslist", applicationslist);
        mav.addObject("ack", ack);
        mav.addObject("error", error);
        return mav;
    }

    @RequestMapping("/add")
    public ModelAndView addJobOffer(@RequestParam(required = false) String error, HttpSession request) {
        ModelAndView mav = new ModelAndView();
        if (request.getAttribute("usertype") == null || request.getAttribute("mail") == null) {
            mav.setViewName("redirect:/joboffers?error=" + URLEncoder.encode("You must be logged in to add a job offer.", java.nio.charset.StandardCharsets.UTF_8));
            return mav;
        }
        if (request.getAttribute("usertype").equals("candidate")) {
            mav.setViewName("redirect:/joboffers?error=" + URLEncoder.encode("You must be logged as a company to add a job offer.", java.nio.charset.StandardCharsets.UTF_8));
            return mav;
        }
        mav.setViewName("joboffer/jobAddForm");
        mav.addObject("fieldslist", fServ.listOfSectors());
        mav.addObject("levels", qServ.listOfQualificationLevels());
        mav.addObject("error", error);
        return mav;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addJobOffer(@RequestParam String title, @RequestParam String qualif, @RequestParam String selectedFields, @RequestParam String desc, HttpSession request) {
        JobOffer j = new JobOffer();
        if ((request.getAttribute("usertype") == null) || request.getAttribute("mail") == null) {
            return "redirect:/joboffers?error=" + URLEncoder.encode("You must be logged in to add a job offer", java.nio.charset.StandardCharsets.UTF_8);
        }
        if (request.getAttribute("usertype").equals("candidate")) {
            return "redirect:/joboffers?error=" + URLEncoder.encode("You must be logged as a company to add a job offer", java.nio.charset.StandardCharsets.UTF_8);
        }
        Company c = cServ.getCompany((String) request.getAttribute("mail"));
        if (c == null) {
            return "redirect:/joboffers?error=" + URLEncoder.encode("Company not found", java.nio.charset.StandardCharsets.UTF_8);
        }
        QualificationLevel q = qServ.findQualificationLevel(qualif);
        Set<Field> f = fServ.findFields(selectedFields);
        j.setCompany(c);
        j.setTitle(title);
        j.setQualificationlevel(q);
        j.setTaskdescription(desc);
        j.setFields(f);
        j.setPublicationdate(Date.from(java.time.Instant.now()));
        jServ.addJobOffer(j);
        if (jServ.findById(j.getId()) == null) {
            return "redirect:/joboffers/add?error=" + URLEncoder.encode("Application not added", java.nio.charset.StandardCharsets.UTF_8);
        }
        return "redirect:/joboffers/" + j.getId() + "?ack=" + URLEncoder.encode("Job offer successfully added", java.nio.charset.StandardCharsets.UTF_8);
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView editJobOffer(@PathVariable Integer id, @RequestParam(required = false) String error, HttpSession request) {
        ModelAndView mav = new ModelAndView();
        JobOffer j = jServ.findById(id);
        if (j == null) {
            mav.setViewName("redirect:/joboffers?error=" + URLEncoder.encode("Job offer not found", java.nio.charset.StandardCharsets.UTF_8));
            return mav;
        }
        if (request.getAttribute("mail") == null) {
            mav.setViewName("redirect:/joboffers/" + id + "?error=" + URLEncoder.encode("You must be logged in to edit a job offer.", java.nio.charset.StandardCharsets.UTF_8));
            return  mav;
        }
        if (!request.getAttribute("mail").equals(j.getCompany().getMail())) {
            mav.setViewName("redirect:/joboffers/" + id + "?error=" + URLEncoder.encode("You must be the owner of the job offer to edit it.", java.nio.charset.StandardCharsets.UTF_8));
            return mav;
        }
        mav.setViewName("joboffer/jobEditForm");
        mav.addObject("job", j);
        mav.addObject("fieldslist", fServ.listOfSectors());
        mav.addObject("levels", qServ.listOfQualificationLevels());
        mav.addObject("error", error);
        return mav;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editJobOffer(@PathVariable Integer id, @RequestParam String title, @RequestParam String qualif, @RequestParam String selectedFields, @RequestParam String taskdescription) {
        JobOffer j = jServ.findById(id);
        if (j == null) {
            return "redirect:/joboffers?error=" + URLEncoder.encode("Job offer not found", java.nio.charset.StandardCharsets.UTF_8);
        }
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
    public String deleteJobOffer(@PathVariable Integer id, HttpSession request) {
        JobOffer j = jServ.findById(id);
        if (j == null) {
            return "redirect:/joboffers?error=" + URLEncoder.encode("Job offer not found", java.nio.charset.StandardCharsets.UTF_8);
        }
        if (request.getAttribute("mail") == null) {
            return "redirect:/joboffers?error=" + URLEncoder.encode("You must be logged in to delete a job offer.", java.nio.charset.StandardCharsets.UTF_8);
        }
        if (!request.getAttribute("mail").equals(j.getCompany().getMail())) {
            return "redirect:/joboffers?error=" + URLEncoder.encode("You must be the owner of the job offer to delete it.", java.nio.charset.StandardCharsets.UTF_8);
        }
        jServ.deleteJobOffer(j);
        return "redirect:/joboffers?ack=" + URLEncoder.encode("Job offer n°" + id + " deleted", java.nio.charset.StandardCharsets.UTF_8);
    }

}

