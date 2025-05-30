package fr.atlantique.imt.inf211.jobmngt.service;

import fr.atlantique.imt.inf211.jobmngt.entity.Candidate;
import fr.atlantique.imt.inf211.jobmngt.entity.Company;
import org.springframework.stereotype.Component;

import java.util.List;

public interface CompanyService {
    List<Company> listOfCompanies();

    Company getCompany(String mail);

    Integer countOfCompanies();

    void editCompany(Company c);

    void addCompany(Company c);

    void deleteCompany(Company c);
}
