package fr.atlantique.imt.inf211.jobmngt.service;


import fr.atlantique.imt.inf211.jobmngt.dao.FieldDao;
import fr.atlantique.imt.inf211.jobmngt.entity.Field;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;


@Component
public class FieldServiceImp implements FieldService {


    @Autowired
    FieldDao qDAO;

    @Transactional(readOnly = true)
    public List<Field> listOfSectors() {
        return qDAO.findAll();
    }

    @Transactional(readOnly = true)
    public Set<Field> findFields(String fields) {
        return qDAO.findFields(fields);
    }

}
