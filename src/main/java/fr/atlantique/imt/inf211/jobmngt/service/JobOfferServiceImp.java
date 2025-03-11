package fr.atlantique.imt.inf211.jobmngt.service;

import fr.atlantique.imt.inf211.jobmngt.dao.JobOfferDao;
import fr.atlantique.imt.inf211.jobmngt.entity.Field;
import fr.atlantique.imt.inf211.jobmngt.entity.JobOffer;
import fr.atlantique.imt.inf211.jobmngt.entity.QualificationLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class JobOfferServiceImp implements JobOfferService {

    @Autowired
    JobOfferDao jobOfferDao;

    @Override
    public List<JobOffer> findByFieldsAndQualif(Set<Field> fields, QualificationLevel qualif) {
        return jobOfferDao.findByFieldsAndQualif(fields, qualif);
    }
}
