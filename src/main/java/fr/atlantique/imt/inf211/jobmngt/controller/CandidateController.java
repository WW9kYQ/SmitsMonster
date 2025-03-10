package fr.atlantique.imt.inf211.jobmngt.controller;


import fr.atlantique.imt.inf211.jobmngt.entity.Candidate;
import fr.atlantique.imt.inf211.jobmngt.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/candidates")
public class CandidateController {

    @Autowired
    CandidateService cServ;

    @RequestMapping("/panel")
    public ModelAndView candidatePanel() {
        ModelAndView mav = new ModelAndView("candidate/candidatePanel");
        mav.addObject("nb_candidates", cServ.countOfCandidates());
        return mav;
    }

    @RequestMapping("")
    public ModelAndView listOfCandidates() {
        ModelAndView mav = new ModelAndView("candidate/candidateList");
        List<Candidate> li = cServ.listOfCandidates();
        mav.addObject("candidateslist", li);
        return mav;
    }


}
