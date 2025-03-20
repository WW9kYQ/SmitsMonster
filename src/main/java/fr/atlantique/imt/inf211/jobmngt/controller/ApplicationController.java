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

import java.net.URLEncoder;
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
    public ModelAndView listOfApplications(@RequestParam(required = false) String error, @RequestParam(required = false) String ack) {
        ModelAndView mav = new ModelAndView("application/applicationList");
        mav.addObject("applicationslist", aServ.listOfApplications());
        mav.addObject("error", error);
        mav.addObject("ack", ack);
        return mav;
    }

    @RequestMapping("/{id}")
    public ModelAndView applicationDetails(@PathVariable Integer id, @RequestParam(required = false) String ack, @RequestParam(required = false) String error) {
        ModelAndView mav = new ModelAndView("application/applicationView");
        Application a = aServ.findById(id);
        if (a == null) {
            return new ModelAndView("redirect:/applications?error=" + URLEncoder.encode("Application not found", java.nio.charset.StandardCharsets.UTF_8));
        }
        List<JobOffer> offerslist = jServ.findByFieldsAndQualif(a.getFields(), a.getQualificationlevel());
        mav.addObject("app", a);
        mav.addObject("fieldslist", fServ.listOfSectors());
        mav.addObject("offerslist", offerslist);
        mav.addObject("ack", ack);
        mav.addObject("error", error);
        return mav;
    }

    @RequestMapping("/add")
    public ModelAndView addApplication(@RequestParam(required = false) String error, HttpSession request) {
        ModelAndView mav = new ModelAndView();
        if (request.getAttribute("usertype") == null || request.getAttribute("mail") == null) {
            mav.setViewName("redirect:/applications?error=" + URLEncoder.encode("You must be logged in to add an application.", java.nio.charset.StandardCharsets.UTF_8));
            return mav;
        }
        if (request.getAttribute("usertype").equals("company")) {
            mav.setViewName("redirect:/applications?error=" + URLEncoder.encode("You must be logged as a candidate to add an application.", java.nio.charset.StandardCharsets.UTF_8));
            return mav;
        }
        mav.setViewName("application/applicationAddForm");

        mav.addObject("fieldslist", fServ.listOfSectors());
        mav.addObject("levels", qServ.listOfQualificationLevels());
        mav.addObject("error", error);
        return mav;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addApplication(@RequestParam String cv, @RequestParam String qualif, @RequestParam String selectedFields, HttpSession request) {
        Application a = new Application();
        if ((request.getAttribute("usertype") == null) || request.getAttribute("mail") == null) {
            return "redirect:/applications?error=" + URLEncoder.encode("You must be logged in to add an application", java.nio.charset.StandardCharsets.UTF_8);
        }
        if (request.getAttribute("usertype").equals("company")) {
            return "redirect:/applications?error=" + URLEncoder.encode("You must be logged as a candidate to add an application", java.nio.charset.StandardCharsets.UTF_8);
        }
        Candidate c = cServ.getCandidate((String) request.getAttribute("mail"));
        if (c == null) {
            return "redirect:/applications?error=" + URLEncoder.encode("Candidate not found", java.nio.charset.StandardCharsets.UTF_8);
        }
        QualificationLevel q = qServ.findQualificationLevel(qualif);
        Set<Field> f = fServ.findFields(selectedFields);
        a.setCandidate(c);
        a.setCv(cv);
        a.setQualificationlevel(q);
        a.setFields(f);
        a.setAppdate(Date.from(java.time.Instant.now()));
        aServ.addApplication(a);
        if (aServ.findById(a.getId()) == null) {
            return "redirect:/applications/add?error=" + URLEncoder.encode("Application not added", java.nio.charset.StandardCharsets.UTF_8);
        }
        return "redirect:/applications/" + a.getId() + "?ack=" + URLEncoder.encode("Application successfully added", java.nio.charset.StandardCharsets.UTF_8);
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView editApplication(@PathVariable Integer id, @RequestParam(required = false) String error, HttpSession request) {
        Application a = aServ.findById(id);
        ModelAndView mav = new ModelAndView();
        if (a == null) {
            mav.setViewName("redirect:/applications?error=" + URLEncoder.encode("Application not found", java.nio.charset.StandardCharsets.UTF_8));
            return mav;
        }
        if (request.getAttribute("mail") == null) {
            mav.setViewName("redirect:/applications/" + id + "?error=" + URLEncoder.encode("You must be logged in to edit an application.", java.nio.charset.StandardCharsets.UTF_8));
            return  mav;
        }
        if (!request.getAttribute("mail").equals(a.getCandidate().getMail())) {
            mav.setViewName("redirect:/applications/" + id + "?error=" + URLEncoder.encode("You must be the owner of the application to edit it.", java.nio.charset.StandardCharsets.UTF_8));
            return mav;
        }
        mav.setViewName("application/applicationEditForm");
        mav.addObject("app", a);
        mav.addObject("fieldslist", fServ.listOfSectors());
        mav.addObject("levels", qServ.listOfQualificationLevels());
        mav.addObject("error", error);
        return mav;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editApplication(@PathVariable Integer id, @RequestParam String cv, @RequestParam String qualif, @RequestParam String selectedFields) {
        Application a = aServ.findById(id);
        if (a == null) {
            return "redirect:/applications?error=" + URLEncoder.encode("Application not found", java.nio.charset.StandardCharsets.UTF_8);
        }
        QualificationLevel q = qServ.findQualificationLevel(qualif);
        Set<Field> f = fServ.findFields(selectedFields);
        a.setCv(cv);
        a.setQualificationlevel(q);
        a.setFields(f);
        a.setAppdate(Date.from(java.time.Instant.now()));
        aServ.editApplication(a);
        if (aServ.findById(a.getId()) == null) {
            return "redirect:/applications?error=" + URLEncoder.encode("Application not edited", java.nio.charset.StandardCharsets.UTF_8);
        }
        return "redirect:/applications/" + a.getId() + "?ack=" + URLEncoder.encode("Application edited", java.nio.charset.StandardCharsets.UTF_8);
    }

    @RequestMapping("/delete/{id}")
    public String deleteApplication(@PathVariable Integer id, HttpSession session) {
        Application a = aServ.findById(id);
        if (a == null) {
            return "redirect:/applications?error=" + URLEncoder.encode("Application not found", java.nio.charset.StandardCharsets.UTF_8);
        }
        if (session.getAttribute("mail") == null) {
            return "redirect:/applications?error=" + URLEncoder.encode("You must be logged in to delete an application.", java.nio.charset.StandardCharsets.UTF_8);
        }
        if (!session.getAttribute("mail").equals(a.getCandidate().getMail())) {
            return "redirect:/applications?error=" + URLEncoder.encode("You must be the owner of the application to delete it.", java.nio.charset.StandardCharsets.UTF_8);
        }
        aServ.deleteApplication(a);
        return "redirect:/applications?ack=" + URLEncoder.encode("Application n°" + id + " deleted", java.nio.charset.StandardCharsets.UTF_8);
    }

}