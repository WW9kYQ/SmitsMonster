package fr.atlantique.imt.inf211.jobmngt.service;

import fr.atlantique.imt.inf211.jobmngt.entity.Field;

import java.util.List;
import java.util.Set;


public interface FieldService {

    List<Field> listOfSectors();

    Set<Field> findFields(String fields);

}
