package fr.atlantique.imt.inf211.jobmngt.controller;


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
    public ModelAndView listCompanies() {
        //model and view is located in the templates/company folder
        ModelAndView mav = new ModelAndView("company/companyList");
        mav.addObject("companieslist", cServ.listOfCompanies());
        return mav;
    }

    @RequestMapping("/{mail}")
    public ModelAndView viewCompany(@PathVariable String mail) {
        Company company = cServ.getCompany(mail);

        if (company == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found");
        }
        System.out.println("Company found: " + company.getMail() + ", " + company.getDenomination());
        ModelAndView mav = new ModelAndView("company/companyView");
        mav.addObject("company", company);
        return mav;
    }

    @RequestMapping("/add")
    public String addCcompany() {
        return "company/companyAddForm";
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public String addCompany(@RequestParam String mail, @RequestParam String password, @RequestParam String denomination, @RequestParam String description, @RequestParam String city) {
        UserApp userApp = new UserApp(mail, password);
        Company c = new Company();
        c.setUserapp(userApp);
        c.setDenomination(denomination);
        c.setDescription(description);
        c.getUserapp().setCity(city);
        cServ.addCompany(c);
        return "redirect:/companies/" + mail;
    }
}
