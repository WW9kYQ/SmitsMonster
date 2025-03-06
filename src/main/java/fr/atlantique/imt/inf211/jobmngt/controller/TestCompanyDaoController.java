package fr.atlantique.imt.inf211.jobmngt.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.atlantique.imt.inf211.jobmngt.dao.CompanyDao;
import fr.atlantique.imt.inf211.jobmngt.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/REST/companies")
public class TestCompanyDaoController {

    @Autowired
    private CompanyDao companyDao;

    /**
     * curl -X GET http://localhost:8080/REST/companies
     */

    @GetMapping
    public ResponseEntity<List<Company>> listCompanies() {
        List<Company> companies = companyDao.findAll();
        return ResponseEntity.ok(companies);
    }


    /*
    * curl -X POST localhost:8080/REST/companies -H \
'Content-type: application/json' -d \
'{"mail": "atlantique@imt.fr", "denomination": "myFirstCompany", "description": "Desc of my new company", "userapp": {"password": "6678", "city": "Brest"}}'
    * */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Company newCompany(@RequestBody Company company) {
        if (company.getMail() == null) {
            throw new IllegalArgumentException("Mail is required");
        }
        company.getUserapp().setMail(company.getMail());
        companyDao.persist(company);
        return company;
    }


    /*
     *curl -X GET "http://localhost:8080/REST/companies/search/testcompany@test.fr" -H "Content-Type: application/json"
     * */
    @GetMapping("/search/{mail}")
    public ResponseEntity<Company> getCompanyByMail(@PathVariable String mail) {
        Company company = companyDao.findByMail(mail);
        return ResponseEntity.ok(company);
    }

    /**
     * curl -X PUT localhost:8080/REST/companies/atlantique%40imt.fr \
     * -H "Content-Type: application/json" \
     * -d "{
     * \"denomination\": \"Updated IMT Atlantique\",
     * \"description\": \"Une école d'ingénieurs généraliste mise a jour\",
     * \"userapp\": {
     * \"mail\": \"atlantique@imt.fr\",
     * \"password\": \"6678\",
     * \"city\": \"Brest\"
     * }
     * }"
     */

    @PutMapping(value = "/{mail}")
    public Company replaceCompany(@RequestBody Company newCompany, @PathVariable String mail) {
        Company company = companyDao.findByMail(mail);
        if (company != null) {
            company.getUserapp().setMail(newCompany.getUserapp().getMail());
            company.getUserapp().setPassword(newCompany.getUserapp().getPassword());
            company.setDenomination(newCompany.getDenomination());
            company.setDescription(newCompany.getDescription());
            return companyDao.merge(company);
        }
        return null;
    }

    /*
    curl -X GET "http://localhost:8080/REST/companies/remove/Updated%20IMT%20Atlantique"
    /
     */
    @GetMapping("/remove/{mail}")
    public ResponseEntity<String> removeCompany(@PathVariable String mail) {
        Company company = companyDao.findByMail(mail);
        companyDao.remove(company);
        return ResponseEntity.ok("Company has been removed");
    }


}
