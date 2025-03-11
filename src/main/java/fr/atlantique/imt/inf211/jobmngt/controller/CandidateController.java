package fr.atlantique.imt.inf211.jobmngt.controller;


import fr.atlantique.imt.inf211.jobmngt.entity.Candidate;
import fr.atlantique.imt.inf211.jobmngt.entity.UserApp;
import fr.atlantique.imt.inf211.jobmngt.service.CandidateService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping("/{mail}")
    public ModelAndView candidateDetails(@PathVariable String mail) {
        ModelAndView mav = new ModelAndView("candidate/candidateView");

        Candidate c = cServ.getCandidate(mail);
        mav.addObject("candidate", c);
        return mav;
    }

    @RequestMapping("/edit/{mail}")
    public ModelAndView editCandidate(@PathVariable String mail) {
        ModelAndView mav = new ModelAndView("candidate/candidateEditForm");

        Candidate c = cServ.getCandidate(mail);
        mav.addObject("candidate", c);
        return mav;
    }

    @RequestMapping(value="/edit/{mail}", method = RequestMethod.POST)
    public String editCandidate(@PathVariable String mail ,@RequestParam String firstname, @RequestParam String lastname, @RequestParam String city) {
        Candidate c = cServ.getCandidate(mail);
        c.setFirstname(firstname);
        c.setLastname(lastname);
        c.getUserapp().setCity(city);

        cServ.editCandidate(c);
        return "redirect:/candidates/" + mail;
    }

    @RequestMapping("/add")
    public String addCandidate() {
        return "candidate/candidateAddForm";
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public String addCandidate(@RequestParam String mail, @RequestParam String password, @RequestParam String firstname, @RequestParam String lastname, @RequestParam String city) {
        UserApp userApp = new UserApp(mail, password);
        Candidate c = new Candidate();
        c.setUserapp(userApp);
        c.setFirstname(firstname);
        c.setLastname(lastname);
        c.getUserapp().setCity(city);

        cServ.addCandidate(c);
        return "redirect:/candidates/" + mail;
    }

    @RequestMapping("/delete/{mail}")
    public String deleteCandidate(@PathVariable String mail, HttpServletRequest request) {
        Candidate c = cServ.getCandidate(mail);
        cServ.deleteCandidate(c);
        HttpSession session = request.getSession();
        session.setAttribute("mail", null);
        session.setAttribute("usertype", null);
        return "redirect:/candidates";
    }


}
