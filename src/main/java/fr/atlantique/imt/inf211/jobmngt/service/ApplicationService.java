package fr.atlantique.imt.inf211.jobmngt.service;

import fr.atlantique.imt.inf211.jobmngt.entity.Application;
import fr.atlantique.imt.inf211.jobmngt.entity.Field;
import fr.atlantique.imt.inf211.jobmngt.entity.JobOffer;
import fr.atlantique.imt.inf211.jobmngt.entity.QualificationLevel;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Set;

public interface ApplicationService {
    Integer countOfApplications();
    List<Application> listOfApplications();
    Application findById(Integer id);
    void addApplication(Application a);
    void editApplication(Application a);
    void deleteApplication(Application a);
    List<Application> findByFieldsAndQualif(Set<Field> fields, QualificationLevel qualif);

}
