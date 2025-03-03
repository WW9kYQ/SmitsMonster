package fr.atlantique.imt.inf211.jobmngt.controller;

import fr.atlantique.imt.inf211.jobmngt.dao.ApplicationDao;
import fr.atlantique.imt.inf211.jobmngt.dao.FieldDao;
import fr.atlantique.imt.inf211.jobmngt.entity.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ApplicationControllerREST {

    @Autowired
    private ApplicationDao appdao;


    @RequestMapping("/findByFieldAndQualif")
    public List<Application> applicationPanel(@RequestParam String field, @RequestParam String qualificationLevel) {
        List<Application> li = appdao.findByFieldAndQualif(field, qualificationLevel);
        System.out.println(li);
        return li;
    }
}