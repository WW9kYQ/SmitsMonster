package fr.atlantique.imt.inf211.jobmngt.controller;


import fr.atlantique.imt.inf211.jobmngt.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CompanyController {

    @Autowired
    private CompanyService sServ;


    @RequestMapping("/companypanel")
    public ModelAndView companyPanel() {
        ModelAndView mav = new ModelAndView("companypanel");
        mav.addObject("nb_companies", 0);
        return mav;
    }

    @RequestMapping("/listcompanies")
    public ModelAndView listCompanies() {
        //model and view is located in the templates/company folder
        ModelAndView mav = new ModelAndView("company/companyList");
        mav.addObject("companieslist", sServ.listOfCompanies());
        return mav;

    }



}
