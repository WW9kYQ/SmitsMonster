package fr.atlantique.imt.inf211.jobmngt.service;

import fr.atlantique.imt.inf211.jobmngt.entity.Application;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ApplicationService {
    Integer countOfApplications();
    List<Application> listOfApplications();
    Application findById(Integer id);
    void addApplication(Application a);
    void editApplication(Application a);
    void deleteApplication(Application a);
}
