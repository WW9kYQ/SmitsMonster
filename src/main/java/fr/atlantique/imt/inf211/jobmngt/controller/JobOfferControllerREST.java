package fr.atlantique.imt.inf211.jobmngt.controller;


import fr.atlantique.imt.inf211.jobmngt.dao.CompanyDao;
import fr.atlantique.imt.inf211.jobmngt.dao.FieldDao;
import fr.atlantique.imt.inf211.jobmngt.dao.JobOfferDao;
import fr.atlantique.imt.inf211.jobmngt.dao.QualificationLevelDao;
import fr.atlantique.imt.inf211.jobmngt.entity.Company;
import fr.atlantique.imt.inf211.jobmngt.entity.Field;
import fr.atlantique.imt.inf211.jobmngt.entity.JobOffer;
import fr.atlantique.imt.inf211.jobmngt.entity.QualificationLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class JobOfferControllerREST {

    @Autowired
    private JobOfferDao jobOfferDao;

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private FieldDao fielddao;

    @Autowired
    private QualificationLevelDao qualificationLevelDao;

    @RequestMapping(value = "/listjobofferREST/company", method = RequestMethod.GET, produces = "application/json")
    public List<JobOffer> listJobOffersByCompany(@RequestParam("denomination") String denomination){
        Company company = companyDao.findByDenomination(denomination);
        List<JobOffer> li = jobOfferDao.findOffersByCompany(company);
        return li;
    }

    @RequestMapping(value = "/listjobofferREST/fieldandqualif", method = RequestMethod.GET, produces = "application/json")
    public List<JobOffer> listJobOffersByFieldAndQualif(@RequestParam("field") String fieldLabel, @RequestParam("qualif") String qualifLabel){
        Field field = fielddao.findByLabel(fieldLabel);
        QualificationLevel qualificationLevel = qualificationLevelDao.findByLabel(qualifLabel);
        List<JobOffer> li = jobOfferDao.findByFieldAndQualif(field, qualificationLevel);
        System.out.println(li);
        return li;
    }
}
