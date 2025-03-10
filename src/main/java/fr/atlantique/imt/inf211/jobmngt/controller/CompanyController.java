package fr.atlantique.imt.inf211.jobmngt.controller;


import fr.atlantique.imt.inf211.jobmngt.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

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



}
