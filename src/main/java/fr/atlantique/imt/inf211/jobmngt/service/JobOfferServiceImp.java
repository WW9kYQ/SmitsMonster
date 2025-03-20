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

    @Override
    public Integer countOfJobOffers() {
        return jobOfferDao.countOfJobOffers();
    }

    @Override
    public List<JobOffer> listOfJobOffers() {
        return jobOfferDao.findAll();
    }

    @Override
    public JobOffer findById(Integer id) {
        return jobOfferDao.findById(id);
    }

    @Override
    public void addJobOffer(JobOffer a) {
        jobOfferDao.persist(a);
    }

    @Override
    public void editJobOffer(JobOffer a) {
        jobOfferDao.merge(a);
    }

    @Override
    public void deleteJobOffer(JobOffer a) {
        jobOfferDao.remove(a);
    }
}
