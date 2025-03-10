package fr.atlantique.imt.inf211.jobmngt.controller;


import fr.atlantique.imt.inf211.jobmngt.dao.CandidateDao;
import fr.atlantique.imt.inf211.jobmngt.entity.Candidate;
import fr.atlantique.imt.inf211.jobmngt.service.CandidateService;
import fr.atlantique.imt.inf211.jobmngt.service.CandidateServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/candidates")
public class CandidateController {

    @Autowired
    CandidateService candidateService;

    @RequestMapping("/panel")
    public ModelAndView candidatePanel() {
        ModelAndView mav = new ModelAndView("candidatepanel");
        mav.addObject("nb_candidates", candidateService.countOfCandidates());
        return mav;
    }

    @RequestMapping("")
    public ModelAndView listOfCandidates() {
        ModelAndView mav = new ModelAndView("candidate/candidateList");
        List<Candidate> li = candidateService.listOfCandidates();
        mav.addObject("candidateslist", li);
        return mav;
    }


}
