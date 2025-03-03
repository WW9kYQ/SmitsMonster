package fr.atlantique.imt.inf211.jobmngt.controller;


import fr.atlantique.imt.inf211.jobmngt.dao.FieldDao;
import fr.atlantique.imt.inf211.jobmngt.entity.Field;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;


import fr.atlantique.imt.inf211.jobmngt.service.FieldService;

import java.util.List;

@RestController
public class FieldControllerREST {

    @Autowired
    private FieldDao fielddao;

    @RequestMapping(value = "/findByLabelREST", method = RequestMethod.GET)
    public List<Field> findByLabel(@RequestParam("label") String label) {
        List<Field> li = fielddao.findByLabel(label);
        System.out.println(li);
        return li;
        }





}