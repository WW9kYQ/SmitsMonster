package fr.atlantique.imt.inf211.jobmngt.controller;

import fr.atlantique.imt.inf211.jobmngt.dao.FieldDao;
import fr.atlantique.imt.inf211.jobmngt.dao.JobOfferDao;
import fr.atlantique.imt.inf211.jobmngt.dao.QualificationLevelDao;
import fr.atlantique.imt.inf211.jobmngt.entity.JobOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/REST/joboffers")
public class TestJobOfferDaoController {

    @Autowired
    private JobOfferDao jobOfferDao;

    @Autowired
    private FieldDao fieldDao;

    @Autowired
    private QualificationLevelDao qualificationLevelDao;

    /*
    curl -X GET http://localhost:8080/REST/joboffers
     */
    @GetMapping
    public ResponseEntity<List<JobOffer>> listJobOffers() {
        List<JobOffer> jobOffers = jobOfferDao.findAll();
        return ResponseEntity.ok(jobOffers);
    }

    /*
    curl -X POST http://localhost:8080/REST/joboffers -H 'Content-type: application/json' -d '{"company" : {"mail" : "testcompany@test.fr"},"qualificationlevel" : {"id" : 5},"title" : "Test Offer Controller","taskdescription" : "This is a test offer","fields" : [ {"id": 2}, {"id":3} ],"publicationdate" : "2020-01-01"}'
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public JobOffer newJobOffer(@RequestBody JobOffer jobOffer) {
        if (jobOffer == null) {
            throw new IllegalArgumentException("JobOffer is required");
        }
        if (jobOffer.getCompany() == null) {
            throw new IllegalArgumentException("Company is required");
        }
        if (jobOffer.getCompany().getMail() == null) {
            throw new IllegalArgumentException("Company mail is required");
        }
        if (jobOffer.getQualificationlevel() == null) {
            throw new IllegalArgumentException("Qualification level is required");
        }
        if (jobOffer.getTitle() == null) {
            throw new IllegalArgumentException("Job title is required");
        }
        if (jobOffer.getTaskdescription() == null) {
            throw new IllegalArgumentException("Job description is required");
        }
        if (jobOffer.getFields() == null || jobOffer.getFields().isEmpty()) {
            throw new IllegalArgumentException("Job fields are required");
        }
        if (jobOffer.getPublicationdate() == null) {
            throw new IllegalArgumentException("Publication date is required");
        }

        jobOfferDao.persist(jobOffer);
        return jobOffer;
    }

    /*
    curl -X GET "http://localhost:8080/REST/joboffers/search?field=Administration&qualif=PhD"
     */
    @GetMapping("/search")
    public ResponseEntity<List<JobOffer>> getJobOfferByFieldAndQualif(@RequestParam("field") String fieldLabel, @RequestParam("qualif") String qualifLabel) {
        List<JobOffer> jobOffer = jobOfferDao.findByFieldAndQualif(fieldDao.findByLabel(fieldLabel), qualificationLevelDao.findByLabel(qualifLabel));
        return ResponseEntity.ok(jobOffer);
    }

    /*
    curl -X PUT "http://localhost:8080/REST/joboffers/update/2" -H 'Content-type: application/json' -d '{"company" : {"mail" : "testcompany@test.fr"},"qualificationlevel" : {"id" : 4},"title" : "Test Offer Controller 2","taskdescription" : "This is a test offer updated","fields" : [ {"id": 8}, {"id":10} ],"publicationdate" : "2025-01-01"}'
     */
    @PutMapping("/update/{id}")
    public JobOffer updateJobOffer(@RequestBody JobOffer jobOffer, @PathVariable int id) {
        System.out.println("dans méthode");
        JobOffer jobOfferToUpdate = jobOfferDao.findById(id);
        System.out.println(jobOfferToUpdate);
        if (jobOfferToUpdate == null) {
            throw new IllegalArgumentException("JobOffer not found");
        }
        jobOfferToUpdate.setCompany(jobOffer.getCompany());
        jobOfferToUpdate.setQualificationlevel(jobOffer.getQualificationlevel());
        jobOfferToUpdate.setTitle(jobOffer.getTitle());
        jobOfferToUpdate.setTaskdescription(jobOffer.getTaskdescription());
        jobOfferToUpdate.setPublicationdate(jobOffer.getPublicationdate());
        jobOfferToUpdate.setFields(jobOffer.getFields());
        jobOfferDao.merge(jobOfferToUpdate);
        return jobOfferToUpdate;
    }

    /*
    curl -X GET "http://localhost:8080/REST/joboffers/remove/3"
     */
    @GetMapping("/remove/{id}")
    public ResponseEntity<String> removeJobOffer(@PathVariable int id) {
        JobOffer jobOffer = jobOfferDao.findById(id);
        jobOfferDao.remove(jobOffer);
        return ResponseEntity.ok("JobOffer removed");
    }

}
