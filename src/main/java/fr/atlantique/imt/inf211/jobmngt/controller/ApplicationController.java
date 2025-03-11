package fr.atlantique.imt.inf211.jobmngt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;


@Controller
@RequestMapping("/applications")
public class ApplicationController {


    @RequestMapping("/panel")
    public String applicationPanel() {

        return "application/applicationPanel";
    }

}