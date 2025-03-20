package fr.atlantique.imt.inf211.jobmngt.controller;


import fr.atlantique.imt.inf211.jobmngt.service.CandidateService;
import fr.atlantique.imt.inf211.jobmngt.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PagesController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private CompanyService companyService;

    @RequestMapping("/")
    public ModelAndView welcomePage(@RequestParam(required = false) String ack) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("countCompanies", companyService.countOfCompanies());
        modelAndView.addObject("countCandidates", candidateService.countOfCandidates());
        modelAndView.addObject("ack", ack);
        return modelAndView;

    }


}