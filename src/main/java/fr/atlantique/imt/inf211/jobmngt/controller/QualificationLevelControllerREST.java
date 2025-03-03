package fr.atlantique.imt.inf211.jobmngt.controller;


import fr.atlantique.imt.inf211.jobmngt.dao.QualificationLevelDao;
import fr.atlantique.imt.inf211.jobmngt.entity.QualificationLevel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;


import fr.atlantique.imt.inf211.jobmngt.service.QualificationLevelService;

@RestController
public class QualificationLevelControllerREST {

    @Autowired
    private QualificationLevelDao qualificationleveldao;

    @RequestMapping("/findqualifbylabelREST")
    public QualificationLevel getQualificationLevelByLabel(@RequestParam("label") String label) {
        QualificationLevel q = qualificationleveldao.findByLabel(label);
        return q;
    }
}