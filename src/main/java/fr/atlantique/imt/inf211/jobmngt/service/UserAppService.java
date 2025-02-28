package fr.atlantique.imt.inf211.jobmngt.service;

import fr.atlantique.imt.inf211.jobmngt.entity.UserApp;

import java.util.List;
import java.util.Optional;


public interface UserAppService {

    List<UserApp> listOfUsers();

    UserApp getUserapp(Integer id);

    Long nbUsers();

    Optional<UserApp> checkLogin(UserApp u);
}
