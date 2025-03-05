package fr.atlantique.imt.inf211.jobmngt.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.atlantique.imt.inf211.jobmngt.dao.CandidateDao;
import fr.atlantique.imt.inf211.jobmngt.dao.CompanyDao;
import fr.atlantique.imt.inf211.jobmngt.entity.Candidate;
import fr.atlantique.imt.inf211.jobmngt.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/REST/candidates")

public class TestCandidateDaoController {
/*
* Dans un fichier nommé TestCandidateDaoController.java définissez des points d’entrée pour :

lister toutes les candidats existants
créer un nouveau candidat à partir d’informations passées dans la requête HTTP ou présentes directement dans le code Java
récupérer les informations d’un candidat à partir de son identifiant passé en paramètre de la requête HTTP
modifier les informations dont les informations sont passées en paramètre de la requête HTTP ou présentes directement dans le code Java
*/
    @Autowired
    private CandidateDao candidateDao;

    /*
    * curl -X GET http://localhost:8080/REST/candidates
    * */
    @GetMapping
    public ResponseEntity<List<Candidate>> listCandidates() {
        List<Candidate> candidates = candidateDao.findAll();
        return ResponseEntity.ok(candidates);
    }
    /*
    curl -X POST http://localhost:8080/REST/candidates \
     -H "Content-Type: application/json" \
     -d '{
          "mail": "john.doe@example.com",
          "firstname": "John",
          "lastname": "Doe",
          "userapp": {
              "password": "securepassword",
              "city": "Paris"
          }
      }'
    * */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Candidate newCandidate(@RequestBody Candidate candidate) {
        if (candidate.getMail() == null) {
            throw new IllegalArgumentException("Mail is required");
        }
        candidate.getUserapp().setMail(candidate.getMail());
        candidateDao.persist(candidate);
        return candidate;
    }

    /*
    curl -X GET http://localhost:8080/REST/candidates/search/john.doe@example.com
    * */
    @GetMapping("/search/{mail}")
    public ResponseEntity<Candidate> getCandidateByMail(@PathVariable String mail) {
        Candidate candidate = candidateDao.findByMail(mail);
        return ResponseEntity.ok(candidate);
    }


    /*
    *
    curl -X PUT http://localhost:8080/REST/candidates/john.doe@example.com \
     -H "Content-Type: application/json" \
     -d '{
          "mail": "john.doe@example.com",
          "firstname": "John",
          "lastname": "New",
          "userapp": {
              "password": "newpassword",
              "city": "Lyon"
          }
      }'
    * */
    @PutMapping(value ="/{mail}")
    public Candidate updateCandidate(@PathVariable String mail, @RequestBody Candidate candidate) {
        if (candidate.getMail() == null) {
            throw new IllegalArgumentException("This Candidate is unknown");
        }
        candidate.getUserapp().setMail(candidate.getMail());
        candidate.getUserapp().setPassword(candidate.getUserapp().getPassword());
        candidateDao.merge(candidate);
        return candidate;
    }


}
