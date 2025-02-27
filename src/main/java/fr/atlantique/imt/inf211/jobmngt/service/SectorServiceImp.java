package fr.atlantique.imt.inf211.jobmngt.service;


import fr.atlantique.imt.inf211.jobmngt.dao.SectorDao;
import fr.atlantique.imt.inf211.jobmngt.entity.Sector;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;


@Component
public class SectorServiceImp implements SectorService {
    

    @Autowired
    SectorDao qDAO;

    @Transactional(readOnly = true)
    public List<Sector> listOfSectors(){
        return qDAO.findAll("id", "ASC");
    }
    
}
