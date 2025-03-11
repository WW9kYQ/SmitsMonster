package fr.atlantique.imt.inf211.jobmngt.service;

import fr.atlantique.imt.inf211.jobmngt.dao.CompanyDao;
import fr.atlantique.imt.inf211.jobmngt.entity.Candidate;
import fr.atlantique.imt.inf211.jobmngt.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDao companyDao;

    @Transactional(readOnly = true)
    public List<Company> listOfCompanies() {
        return companyDao.findAll();
    }

    @Transactional(readOnly = true)
    public Integer countOfCompanies() {
        return companyDao.count();
    }

    @Transactional(readOnly = true)
    public Company getCompany(String mail) {
        return companyDao.findByMail(mail);
    }

    @Transactional
    public void editCompany(Company c) {
        companyDao.merge(c);
    }

    @Transactional
    public void addCompany(Company c) {
        companyDao.persist(c);
    }

    @Transactional
    public void deleteCompany(Company c) {
        companyDao.remove(c);
    }
}

