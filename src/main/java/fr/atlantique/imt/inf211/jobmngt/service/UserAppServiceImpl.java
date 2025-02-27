package fr.atlantique.imt.inf211.jobmngt.service;


import fr.atlantique.imt.inf211.jobmngt.dao.UserAppDao;
import fr.atlantique.imt.inf211.jobmngt.entity.UserApp;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;


@Component
public class UserAppServiceImpl implements UserAppService {
    

    @Autowired
    UserAppDao cDAO;

    @Transactional(readOnly = true)
    public List<UserApp> listOfUsers(){
        return cDAO.findAll("name", "ASC");
    }
    
    @Transactional(readOnly = true)
    public UserApp getUserapp(Integer id){
        return cDAO.findById(id);
    }

    @Transactional(readOnly = true)
    public Long nbUsers(){
        return  cDAO.nbUsers();
    }

    @Transactional(readOnly = true)
    public Optional<UserApp> checkLogin(UserApp u){
        return cDAO.checkLogin(u);
    }
}
