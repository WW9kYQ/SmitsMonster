package fr.atlantique.imt.inf211.jobmngt.service;

import fr.atlantique.imt.inf211.jobmngt.dao.CandidateDao;
import fr.atlantique.imt.inf211.jobmngt.entity.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CandidateServiceImp implements CandidateService {

    @Autowired
    private CandidateDao candidateDao;

    @Transactional(readOnly = true)
    public List<Candidate> listOfCandidates() {
        return candidateDao.findAll();
    }

    @Transactional(readOnly = true)
    public Integer countOfCandidates() {
        return candidateDao.count();
    }
}
