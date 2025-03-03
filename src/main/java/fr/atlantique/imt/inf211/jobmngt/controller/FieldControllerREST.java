package fr.atlantique.imt.inf211.jobmngt.controller;


import fr.atlantique.imt.inf211.jobmngt.dao.FieldDao;
import fr.atlantique.imt.inf211.jobmngt.entity.Field;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;


import fr.atlantique.imt.inf211.jobmngt.service.FieldService;

import java.util.List;

@RestController
public class FieldControllerREST {

    @Autowired
    private FieldDao fielddao;

    @RequestMapping(value = "/findfieldbylabelREST", method = RequestMethod.GET)
    public Field findByLabel(@RequestParam("label") String label) {
        Field f = fielddao.findByLabel(label);
        return f;
    }


}