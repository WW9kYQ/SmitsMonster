package fr.atlantique.imt.inf211.jobmngt.controller;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import fr.atlantique.imt.inf211.jobmngt.dao.ApplicationDao;
import fr.atlantique.imt.inf211.jobmngt.dao.FieldDao;
import fr.atlantique.imt.inf211.jobmngt.dao.QualificationLevelDao;
import fr.atlantique.imt.inf211.jobmngt.entity.Application;
import fr.atlantique.imt.inf211.jobmngt.entity.Field;
import fr.atlantique.imt.inf211.jobmngt.entity.QualificationLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;


@RestController
public class ApplicationControllerREST {

    @Autowired
    private ApplicationDao appdao;

    @Autowired
    private FieldDao fielddao;

    @Autowired
    private QualificationLevelDao qualificationLevelDao;


    @RequestMapping(value = "/findByFieldAndQualifREST", method = RequestMethod.GET)
    public List<Application> applicationPanel(@RequestParam("field") String fieldLabel, @RequestParam("qualif") String qualifLabel) {
        Field field = fielddao.findByLabel(fieldLabel);
        QualificationLevel qualificationLevel = qualificationLevelDao.findByLabel(qualifLabel);
        List<Application> li = appdao.findByFieldAndQualif(field, qualificationLevel);
        System.out.println(li);
        return li;
    }
}