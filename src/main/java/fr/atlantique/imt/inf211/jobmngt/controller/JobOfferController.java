package fr.atlantique.imt.inf211.jobmngt.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;


@Controller
@RequestMapping("/joboffers")
public class JobOfferController {

    @RequestMapping("/panel")
    public String jobofferPanel() {

        return "joboffer/jobPanel";
    }
}
