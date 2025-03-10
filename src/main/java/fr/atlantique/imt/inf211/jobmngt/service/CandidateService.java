package fr.atlantique.imt.inf211.jobmngt.service;

import fr.atlantique.imt.inf211.jobmngt.entity.Candidate;

import java.util.List;

public interface CandidateService {
    List<Candidate> listOfCandidates();
    Integer countOfCandidates();
    Candidate getCandidate(String mail);
    void editCandidate(Candidate c);
    void addCandidate(Candidate c);
    void deleteCandidate(Candidate c);

}
