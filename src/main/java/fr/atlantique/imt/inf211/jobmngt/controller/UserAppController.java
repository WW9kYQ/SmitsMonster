package fr.atlantique.imt.inf211.jobmngt.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.List;

import fr.atlantique.imt.inf211.jobmngt.service.UserAppService;
import fr.atlantique.imt.inf211.jobmngt.entity.UserApp;
@Controller
public class UserAppController {

    @Autowired
    UserAppService compRepo;

    // Login form
	@RequestMapping("/Userapppanel")
	public ModelAndView companypanel() {
        long nb = 0;
        ModelAndView mav = new ModelAndView("/Userapppanel.html");
        nb = compRepo.nbUsers();
        mav.addObject("nb_users", nb);
        return mav;
	}

    // @RequestMapping(value = "/companydetails")
    // public ModelAndView companydetails(@RequestParam(name = "comp") Integer compid) {
    //     ModelAndView mav = new ModelAndView("/companydetails.html");
    //     Company c = compRepo.getCompany(compid);
    //     mav.addObject("company", c);
    //     return mav;
	// }
    
    @RequestMapping("/listuserapps")
    public ModelAndView listUsers(){
        ModelAndView mav = new ModelAndView("/companylist.html");
        List<UserApp> li = compRepo.listOfUsers();
        mav.addObject("userslist", li);
        return mav;
    }
}