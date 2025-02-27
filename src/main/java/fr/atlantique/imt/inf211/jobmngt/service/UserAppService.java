package fr.atlantique.imt.inf211.jobmngt.service;

import fr.atlantique.imt.inf211.jobmngt.entity.UserApp;
import java.util.List;
import java.util.Optional;


public interface UserAppService {

    public List<UserApp> listOfUsers();
    
    public UserApp getUserapp(Integer id);

    public Long nbUsers();

    public Optional<UserApp> checkLogin(UserApp u);
}
