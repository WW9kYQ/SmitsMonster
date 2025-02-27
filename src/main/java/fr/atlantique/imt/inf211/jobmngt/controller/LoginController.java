package fr.atlantique.imt.inf211.jobmngt.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import fr.atlantique.imt.inf211.jobmngt.entity.UserApp;
import fr.atlantique.imt.inf211.jobmngt.service.UserAppService;

@Controller
public class LoginController {

    //value injected from the application.properties file
    @Value("${jobmngt.admin}")
    private String adminLogin;

    @Autowired
    UserAppService compRepo;

    // Login form
	@RequestMapping("/login")
	public String login() {
		return "login.html";
	}

    // Login form
    //post method

    /**
     * @param u
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/checklogin")
    public ModelAndView checkLog(@ModelAttribute UserApp u, HttpServletRequest request){
        ModelAndView mav= new ModelAndView();
        HttpSession session = request.getSession();
        Optional<UserApp> user = compRepo.checkLogin(u);
        if( user.isPresent()){
            u = user.get();
            System.out.println("User found uid: "+u.getId()+" usertype: "+ u.getUserType());
            mav.addObject("user", u);
            session.setAttribute("uid", u.getId());
            session.setAttribute("usertype", u.getUserType());
            mav.setViewName("index");

        }else{
            mav.addObject("error", "Password or username incorrect");
            mav.setViewName("login");

        }
        return mav;
    }

    @RequestMapping("/logout")
	public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("uid", null);
        session.setAttribute("usertype", null);
        return "index.html";
    }

}