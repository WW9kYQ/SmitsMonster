package fr.atlantique.imt.inf211.jobmngt.service;


import fr.atlantique.imt.inf211.jobmngt.dao.QualificationLevelDao;
import fr.atlantique.imt.inf211.jobmngt.entity.QualificationLevel;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Component
public class QualificationLevelServiceImp implements QualificationLevelService {


    @Autowired
    QualificationLevelDao qDAO;

    @Transactional(readOnly = true)
    public List<QualificationLevel> listOfQualificationLevels() {
        return qDAO.findAll();
    }

    @Transactional(readOnly = true)
    public QualificationLevel findQualificationLevel(String qualif) {
        return qDAO.findByLabel(qualif);
    }

}
