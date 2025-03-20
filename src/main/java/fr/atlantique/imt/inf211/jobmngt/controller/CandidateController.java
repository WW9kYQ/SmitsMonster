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
import org.springframework.ui.Model;

import java.net.URLEncoder;
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
    public ModelAndView listOfCandidates(@RequestParam(required = false) String error, @RequestParam(required = false) String ack) {
        ModelAndView mav = new ModelAndView("candidate/candidateList");
        List<Candidate> li = cServ.listOfCandidates();
        mav.addObject("candidateslist", li);
        mav.addObject("error", error);
        mav.addObject("ack", ack);
        return mav;
    }

    @RequestMapping("/{mail}")
    public ModelAndView candidateDetails(@PathVariable String mail) {
        ModelAndView mav = new ModelAndView("candidate/candidateView");
        Candidate c;
        try {
            c = cServ.getCandidate(mail);
        } catch (RuntimeException re) {
            return new ModelAndView("redirect:/candidates?error=" + URLEncoder.encode("Candiddate not found", java.nio.charset.StandardCharsets.UTF_8));
        }
        mav.addObject("candidate", c);
        return mav;
    }

    @RequestMapping("/edit/{mail}")
    public ModelAndView editCandidate(@PathVariable String mail, HttpSession request) {
        ModelAndView mav = new ModelAndView();
        if (request.getAttribute("mail") == null) {
            mav.setViewName("redirect:/candidates?error=" + URLEncoder.encode("You must be logged in to add a candidate.", java.nio.charset.StandardCharsets.UTF_8));
            return mav;
        }
        if (request.getAttribute("usertype").equals("company")) {
            mav.setViewName("redirect:/candidates?error=" + URLEncoder.encode("You must be logged as a candidate to edit a candidate.", java.nio.charset.StandardCharsets.UTF_8));
            return mav;
        }
        if (request.getAttribute("mail") == mail) {
            mav.setViewName("redirect:/candidates?error=" + URLEncoder.encode("You must be logged as the candidate to edit it.", java.nio.charset.StandardCharsets.UTF_8));
            return mav;
        }
        mav.setViewName("candidate/candidateEditForm");
        Candidate c;
        try {
            c = cServ.getCandidate(mail);
        } catch (RuntimeException re) {
            mav.setViewName("redirect:/candidates?error=" + URLEncoder.encode("Candidate not found", java.nio.charset.StandardCharsets.UTF_8));
            return mav;
        }
        mav.addObject("candidate", c);
        return mav;
    }

    @RequestMapping(value = "/edit/{mail}", method = RequestMethod.POST)
    public String editCandidate(@PathVariable String mail, @RequestParam String firstname, @RequestParam String lastname, @RequestParam String city) {
        Candidate c;
        try {
            c = cServ.getCandidate(mail);
        } catch (RuntimeException re) {
            return "redirect:/candidates?error=" + URLEncoder.encode("Candidate not found", java.nio.charset.StandardCharsets.UTF_8);
        }
        c.setFirstname(firstname);
        c.setLastname(lastname);
        c.getUserapp().setCity(city);
        try {
            cServ.editCandidate(c);
        } catch (RuntimeException re) {
            return "redirect:/candidates?error=" + URLEncoder.encode("Error while editing candidate", java.nio.charset.StandardCharsets.UTF_8);
        }
        return "redirect:/candidates/" + mail;
    }

    @RequestMapping("/add")
    public String addCandidate() {
        return "candidate/candidateAddForm";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addCandidate(@RequestParam String mail, @RequestParam String password, @RequestParam String firstname, @RequestParam String lastname, @RequestParam String city, Model model) {
        if (!mail.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            model.addAttribute("mailError", "Invalid email format");
            return "candidate/candidateAddForm";
        }
        if (password.length() < 4) {
            model.addAttribute("passwordError", "Password must be at least 4 characters long");
            return "candidate/candidateAddForm";
        }
        UserApp userApp = new UserApp(mail, password);
        Candidate c = new Candidate();
        c.setUserapp(userApp);
        c.setFirstname(firstname);
        c.setLastname(lastname);
        c.getUserapp().setCity(city);
        try {
            cServ.addCandidate(c);
        } catch (RuntimeException re) {
            return "redirect:/candidates?error=" + URLEncoder.encode("Candidate not added", java.nio.charset.StandardCharsets.UTF_8);
        }
        return "redirect:/candidates/" + mail;
    }

    @RequestMapping("/delete/{mail}")
    public String deleteCandidate(@PathVariable String mail, HttpSession session) {
        Candidate c;
        try {
            c = cServ.getCandidate(mail);
        } catch (RuntimeException re) {
            return "redirect:/candidates?error=" + URLEncoder.encode("Candidate not found", java.nio.charset.StandardCharsets.UTF_8);
        }
        if (session.getAttribute("mail") == null) {
            return "redirect:/candidates?error=" + URLEncoder.encode("You must be logged in to delete a candidate.", java.nio.charset.StandardCharsets.UTF_8);
        }
        if (!session.getAttribute("mail").equals(c.getMail())) {
            return "redirect:/candidates?error=" + URLEncoder.encode("You must be the owner of the candidate to delete it.", java.nio.charset.StandardCharsets.UTF_8);
        }
        try {
            cServ.deleteCandidate(c);
        } catch (RuntimeException re) {
            return "redirect:/candidates?error=" + URLEncoder.encode("Error while deleting candidate", java.nio.charset.StandardCharsets.UTF_8);
        }
        return "redirect:/login/logout?redirect=/candidates&ack=" + URLEncoder.encode("Candidate deleted successfully", java.nio.charset.StandardCharsets.UTF_8);
    }


}
