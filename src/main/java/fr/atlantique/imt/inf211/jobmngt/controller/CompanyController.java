package fr.atlantique.imt.inf211.jobmngt.controller;


import fr.atlantique.imt.inf211.jobmngt.service.UserAppService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import fr.atlantique.imt.inf211.jobmngt.entity.Candidate;
import fr.atlantique.imt.inf211.jobmngt.entity.Company;
import fr.atlantique.imt.inf211.jobmngt.entity.UserApp;
import fr.atlantique.imt.inf211.jobmngt.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.net.URLEncoder;
import java.util.HashSet;

@Controller
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService cServ;


    @RequestMapping("/panel")
    public ModelAndView companyPanel() {
        ModelAndView mav = new ModelAndView("company/companyPanel");
        mav.addObject("nb_companies", cServ.countOfCompanies());
        return mav;
    }

    @RequestMapping("")
    public ModelAndView listCompanies(@RequestParam(required = false) String error, @RequestParam(required = false) String ack) {
        //model and view is located in the templates/company folder
        ModelAndView mav = new ModelAndView("company/companyList");
        mav.addObject("companieslist", cServ.listOfCompanies());
        mav.addObject("error", error);
        mav.addObject("ack", ack);
        return mav;
    }

    @RequestMapping("/{mail}")
    public ModelAndView viewCompany(@PathVariable String mail, @RequestParam(required = false) String error, @RequestParam(required = false) String ack) {
        Company company;
        ModelAndView mav = new ModelAndView();
        try {
            company = cServ.getCompany(mail);
        } catch (RuntimeException re) {
            mav.setViewName("redirect:/companies?error=" + URLEncoder.encode("Company not found", java.nio.charset.StandardCharsets.UTF_8));
            return mav;
        }
        System.out.println("Company found: " + company.getMail() + ", " + company.getDenomination());
        mav.setViewName("company/companyView");
        mav.addObject("company", company);
        mav.addObject("error", error);
        mav.addObject("ack", ack);
        return mav;
    }

    @RequestMapping("/add")
    public String addCcompany() {
        return "company/companyAddForm";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addCompany(@RequestParam String mail, @RequestParam String password, @RequestParam String denomination, @RequestParam String description, @RequestParam String city, Model model) {
        //check that email is  a mail with regex
        if (!mail.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            model.addAttribute("mailError", "Invalid email format");
            return "company/companyAddForm";
        }
        if (password.length() < 4) {
            model.addAttribute("passwordError", "Password must be at least 4 characters long");
            return "company/companyAddForm";
        }
        UserApp userApp = new UserApp(mail, password);
        Company c = new Company();
        c.setUserapp(userApp);
        c.setDenomination(denomination);
        c.setDescription(description);
        c.getUserapp().setCity(city);
        try {
            cServ.addCompany(c);
        } catch (RuntimeException re) {
            return "redirect:/companies?error=" + URLEncoder.encode("Company not added", java.nio.charset.StandardCharsets.UTF_8);
        }
        return "redirect:/companies/" + mail;

    }

    @RequestMapping("/delete/{mail}")
    public String removeCompany(@PathVariable String mail, HttpSession session) {
        Company c;
        try {
            c = cServ.getCompany(mail);
        } catch (RuntimeException re) {
            return "redirect:/companies?error=" + URLEncoder.encode("Company not found", java.nio.charset.StandardCharsets.UTF_8);
        }
        if (session.getAttribute("mail") == null) {
            return "redirect:/companies?error=" + URLEncoder.encode("You must be logged in to delete a company.", java.nio.charset.StandardCharsets.UTF_8);
        }
        if (!session.getAttribute("mail").equals(c.getMail())) {
            return "redirect:/companies?error=" + URLEncoder.encode("You must be the owner of the company to delete it.", java.nio.charset.StandardCharsets.UTF_8);
        }
        try {
            cServ.deleteCompany(c);
        } catch (RuntimeException re) {
            return "redirect:/companies?error=" + URLEncoder.encode("Company not removed", java.nio.charset.StandardCharsets.UTF_8);
        }

        return "redirect:/login/logout?redirect=/companies&ack=" + URLEncoder.encode("Company deleted successfully", java.nio.charset.StandardCharsets.UTF_8);
    }

    @RequestMapping("/edit/{mail}")
    public ModelAndView editCompany(@PathVariable String mail, HttpSession request) {
        ModelAndView mav = new ModelAndView();
        Company c;
        try {
            c = cServ.getCompany(mail);
        } catch (RuntimeException re) {
            mav.setViewName("redirect:/companies?error=" + URLEncoder.encode("Company not found", java.nio.charset.StandardCharsets.UTF_8));
            return mav;
        }
        if (request.getAttribute("mail") == null) {
            mav.setViewName("redirect:/companies/" + mail + "?error=" + URLEncoder.encode("You must be logged in to edit a company.", java.nio.charset.StandardCharsets.UTF_8));
            return mav;
        }
        if (!request.getAttribute("mail").equals(c.getMail())) {
            mav.setViewName("redirect:/companies/" + mail + "?error=" + URLEncoder.encode("You must be the owner of the company to edit it.", java.nio.charset.StandardCharsets.UTF_8));
            return mav;
        }
        mav.setViewName("company/companyEditForm");
        mav.addObject("company", c);
        return mav;
    }

    @RequestMapping(value = "/edit/{mail}", method = RequestMethod.POST)
    public String editCompany(@PathVariable String mail, @RequestParam String description, @RequestParam String denomination, @RequestParam String city, HttpSession request) {
        Company c;
        try {
            c = cServ.getCompany(mail);
        } catch (RuntimeException re) {
            return "redirect:/companies?error=" + URLEncoder.encode("Company not found", java.nio.charset.StandardCharsets.UTF_8);
        }
        if (request.getAttribute("mail") == null) {
            return "redirect:/companies/" + mail + "?error=" + URLEncoder.encode("You must be logged in to edit a company.", java.nio.charset.StandardCharsets.UTF_8);
        }
        if (!request.getAttribute("mail").equals(c.getMail())) {
            return "redirect:/companies/" + mail + "?error=" + URLEncoder.encode("You must be the owner of the company to edit it.", java.nio.charset.StandardCharsets.UTF_8);
        }
        c.setDescription(description);
        c.setDenomination(denomination);
        c.getUserapp().setCity(city);
        try {
            cServ.editCompany(c);
        } catch (RuntimeException re) {
            return "redirect:/companies?error=" + URLEncoder.encode("Error while editing company", java.nio.charset.StandardCharsets.UTF_8);
        }
        return "redirect:/companies/" + mail;
    }

}
