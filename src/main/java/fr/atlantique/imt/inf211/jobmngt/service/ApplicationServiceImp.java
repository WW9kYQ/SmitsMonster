package fr.atlantique.imt.inf211.jobmngt.service;

import fr.atlantique.imt.inf211.jobmngt.dao.ApplicationDao;
import fr.atlantique.imt.inf211.jobmngt.entity.Application;
import fr.atlantique.imt.inf211.jobmngt.entity.Field;
import fr.atlantique.imt.inf211.jobmngt.entity.QualificationLevel;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class ApplicationServiceImp implements ApplicationService {

    @Autowired
    ApplicationDao applicationDao;

    @Transactional(readOnly = true)
    public Integer countOfApplications() {
        return applicationDao.countOfApplications();
    }

    @Transactional(readOnly = true)
    public List<Application> listOfApplications() {
        return applicationDao.findAll();
    }

    @Transactional(readOnly = true)
    public Application findById(Integer id) {
        return applicationDao.findById(id);
    }

    @Transactional
    public void addApplication(Application a) {
        applicationDao.persist(a);
    }

    @Transactional
    public void editApplication(Application a) {
        applicationDao.merge(a);
    }

    @Transactional
    public void deleteApplication(Application a) {
        applicationDao.remove(a);
    }

    @Transactional
    public List<Application> findByFieldsAndQualif(Set<Field> fields, QualificationLevel qualif) {
        return applicationDao.findByFieldsAndQualif(fields, qualif);
    }
}
