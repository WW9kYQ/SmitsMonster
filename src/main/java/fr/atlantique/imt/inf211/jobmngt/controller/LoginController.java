package fr.atlantique.imt.inf211.jobmngt.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.net.URL;
import java.net.URLEncoder;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import fr.atlantique.imt.inf211.jobmngt.entity.UserApp;
import fr.atlantique.imt.inf211.jobmngt.service.UserAppService;

@Controller
@RequestMapping("/login")
public class LoginController {

    //value injected from the application.properties file
    @Value("${jobmngt.admin}")
    private String adminLogin;

    @Autowired
    UserAppService compRepo;

    // Login form
    @RequestMapping("")
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
    @RequestMapping(method = RequestMethod.POST, value = "/check")
    public ModelAndView checkLog(@ModelAttribute UserApp u, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        HttpSession session = request.getSession();
        System.out.println(u);
        Optional<UserApp> user = compRepo.checkLogin(u);
        if (user.isPresent()) {
            u = user.get();
            System.out.println("User found mail: " + u.getMail() + " usertype: " + compRepo.getUserType(u.getMail()));
            mav.addObject("user", u);
            session.setAttribute("mail", u.getMail());
            session.setAttribute("usertype", compRepo.getUserType(u.getMail()));
            mav.setViewName("redirect:/");

        } else {
            mav.addObject("error", "Password or username incorrect");
            mav.setViewName("login");

        }
        return mav;
    }

    @RequestMapping("/logout")
    public String logout(@RequestParam(required = false) String redirect, HttpServletRequest request, @RequestParam(required = false) String ack) {
        HttpSession session = request.getSession();
        session.setAttribute("mail", null);
        session.setAttribute("usertype", null);
        System.out.println(redirect);
        if (redirect != null) {
            return "redirect:" + redirect + "?ack=" + URLEncoder.encode(ack, java.nio.charset.StandardCharsets.UTF_8);
        }
        return "redirect:/?ack=" + URLEncoder.encode("Log out successful", java.nio.charset.StandardCharsets.UTF_8);
    }

}