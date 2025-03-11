package fr.atlantique.imt.inf211.jobmngt.service;

import fr.atlantique.imt.inf211.jobmngt.entity.Field;
import fr.atlantique.imt.inf211.jobmngt.entity.QualificationLevel;
import fr.atlantique.imt.inf211.jobmngt.entity.JobOffer;

import java.util.List;
import java.util.Set;

public interface JobOfferService {
    List<JobOffer> findByFieldsAndQualif(Set<Field> fields, QualificationLevel qualif);

}
