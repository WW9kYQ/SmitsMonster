package fr.atlantique.imt.inf211.jobmngt.service;

import fr.atlantique.imt.inf211.jobmngt.entity.Application;
import fr.atlantique.imt.inf211.jobmngt.entity.Field;
import fr.atlantique.imt.inf211.jobmngt.entity.QualificationLevel;
import fr.atlantique.imt.inf211.jobmngt.entity.JobOffer;

import java.util.List;
import java.util.Set;

public interface JobOfferService {
    List<JobOffer> findByFieldsAndQualif(Set<Field> fields, QualificationLevel qualif);
    Integer countOfJobOffers();
    List<JobOffer> listOfJobOffers();
    JobOffer findById(Integer id);
    void addJobOffer(JobOffer a);
    void editJobOffer(JobOffer a);
    void deleteJobOffer(JobOffer a);
}
