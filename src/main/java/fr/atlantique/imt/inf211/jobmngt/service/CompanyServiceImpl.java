package fr.atlantique.imt.inf211.jobmngt.service;

import fr.atlantique.imt.inf211.jobmngt.dao.CompanyDao;
import fr.atlantique.imt.inf211.jobmngt.dao.QualificationLevelDao;
import fr.atlantique.imt.inf211.jobmngt.entity.Company;
import fr.atlantique.imt.inf211.jobmngt.entity.QualificationLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyDao qDAO;

    @Transactional(readOnly = true)
    public List<Company> listOfCompanies() {
        return qDAO.findAll();
    }

    @Transactional(readOnly = true)
    public Integer countOfCompanies() {
        return qDAO.count();
    }

    @Transactional(readOnly = true)
    public Company getCompany(String mail) {
        return qDAO.findByMail(mail);
    }
}
