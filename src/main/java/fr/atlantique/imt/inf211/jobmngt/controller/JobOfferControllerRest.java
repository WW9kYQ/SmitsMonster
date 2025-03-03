package fr.atlantique.imt.inf211.jobmngt.controller;


import fr.atlantique.imt.inf211.jobmngt.dao.JobOfferDao;
import fr.atlantique.imt.inf211.jobmngt.entity.JobOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class JobOfferControllerRest {

    @Autowired
    private JobOfferDao jobOfferDao;

    @RequestMapping(value = "/listjobofferrest", method = RequestMethod.GET)
    public List<JobOffer> jobofferPanel(@RequestParam(value="company") String mail){
        List<JobOffer> li = jobOfferDao.findOffersByCompany(mail);
        System.out.println(li);
//        li.clear();
        return li;
    }
}
