package fr.atlantique.imt.inf211.jobmngt.controller;

import fr.atlantique.imt.inf211.jobmngt.dao.ApplicationDao;
import fr.atlantique.imt.inf211.jobmngt.dao.FieldDao;
import fr.atlantique.imt.inf211.jobmngt.dao.QualificationLevelDao;
import fr.atlantique.imt.inf211.jobmngt.entity.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/REST/applications")
public class TestApplicationDaoController {

    @Autowired
    private ApplicationDao applicationDao;

    @Autowired
    private FieldDao fieldDao;

    @Autowired
    private QualificationLevelDao qualificationLevelDao;

    /*
    curl -X GET "http://localhost:8080/REST/applications"
     */
    @GetMapping
    public ResponseEntity<List<Application>> listApplications() {
        List<Application> applications = applicationDao.findAll();
        return ResponseEntity.ok(applications);
    }

    /*
    curl -X POST http://localhost:8080/REST/applications -H 'Content-type: application/json' -d '{"candidate" : { "mail" : "testcandidate@test.fr"}, "qualificationlevel" : {"id" : 5}, "cv" : "testcv", "appdate" : "2020-01-01", "fields" : [ {"id":1}, {"id":2}]}'
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Application newApplication(@RequestBody Application application) {
        if (application == null) {
            throw new IllegalArgumentException("Application is required");
        }
        if (application.getCandidate() == null) {
            throw new IllegalArgumentException("Candidate is required");
        }
        if (application.getCandidate().getMail() == null) {
            throw new IllegalArgumentException("Candidate mail is required");
        }
        if (application.getQualificationlevel() == null) {
            throw new IllegalArgumentException("Qualification level is required");
        }
        if (application.getCv() == null) {
            throw new IllegalArgumentException("CV is required");
        }
        if (application.getFields() == null || application.getFields().isEmpty()) {
            throw new IllegalArgumentException("Job fields are required");
        }
        if (application.getAppdate() == null) {
            throw new IllegalArgumentException("Application date is required");
        }
        applicationDao.persist(application);
        return application;
    }

    /*
    curl -X GET "http://localhost:8080/REST/applications/search?field=Administration&qualif=PhD"
     */
    @GetMapping("/search")
    public ResponseEntity<List<Application>> getJobOfferByFieldAndQualif(@RequestParam("field") String fieldLabel, @RequestParam("qualif") String qualifLabel) {
        List<Application> jobOffer = applicationDao.findByFieldAndQualif(fieldDao.findByLabel(fieldLabel), qualificationLevelDao.findByLabel(qualifLabel));
        return ResponseEntity.ok(jobOffer);
    }

    /*
    curl -X PUT "http://localhost:8080/REST/applications/update/2" -H 'Content-type: application/json' -d '{"candidate" : { "mail" : "testcandidate@test.fr"}, "qualificationlevel" : {"id" : 3}, "cv" : "testcvupdated", "appdate" : "2025-01-01", "fields" : [ {"id":6}, {"id":9}]}'
     */
    @PutMapping("/update/{id}")
    public Application updateApplication(@RequestBody Application application, @PathVariable int id) {
        Application applicationToUpdate = applicationDao.findById(id);
        if (applicationToUpdate != null) {
            applicationToUpdate.setCandidate(application.getCandidate());
            applicationToUpdate.setQualificationlevel(application.getQualificationlevel());
            applicationToUpdate.setCv(application.getCv());
            applicationToUpdate.setAppdate(application.getAppdate());
            applicationToUpdate.setFields(application.getFields());
            applicationDao.merge(applicationToUpdate);
        }
        return applicationToUpdate;
    }

    /*
    curl -X GET "http://localhost:8080/REST/applications/remove/2"
     */
    @GetMapping("/remove/{id}")
    public ResponseEntity<String> removeJobOffer(@PathVariable int id) {
        Application application = applicationDao.findById(id);
        applicationDao.remove(application);
        return ResponseEntity.ok("Application removed");
    }
}
