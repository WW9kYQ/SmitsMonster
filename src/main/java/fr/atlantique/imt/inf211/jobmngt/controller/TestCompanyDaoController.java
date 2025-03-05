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
@RequestMapping("/companies")
public class TestCompanyDaoController {

    @Autowired
    private CompanyDao companyDao;

    @GetMapping
    public ResponseEntity<List<Company>> listCompanies() {
        List<Company> companies = companyDao.findAll();
        return ResponseEntity.ok(companies);
    }


    /*
    * curl -X POST localhost:8080/companies -H \
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
    *curl -X GET "http://localhost:8080/companies/search/TestCompany" -H "Content-Type: application/json"
     * */
    @GetMapping("/search/{denomination}")
    public ResponseEntity<Company> getCompanyByDenomination(@PathVariable String denomination) {
        Company company = companyDao.findByDenomination(denomination);
        return ResponseEntity.ok(company);
    }
/**
 curl -X PUT localhost:8080/companies/atlantique%40imt.fr \
 -H "Content-Type: application/json" \
 -d "{
 \"denomination\": \"Updated IMT Atlantique\",
 \"description\": \"Une école d'ingénieurs généraliste mise a jour\",
 \"userapp\": {
 \"mail\": \"atlantique@imt.fr\",
 \"password\": \"6678\",
 \"city\": \"Brest\"
 }
 }"
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




}
