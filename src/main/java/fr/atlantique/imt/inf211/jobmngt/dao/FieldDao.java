package fr.atlantique.imt.inf211.jobmngt.dao;
// Generated 28 févr. 2025, 21:17:05 by Hibernate Tools 5.6.15.Final


import fr.atlantique.imt.inf211.jobmngt.entity.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

/**
 * Home object for domain model class Field.
 *
 * @author Hibernate Tools
 * @see .Field
 */
@Repository
public class FieldDao {

    private static final Logger logger = Logger.getLogger(FieldDao.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public Field findById(int id) {
        logger.log(Level.INFO, "getting Field instance with id: " + id);
        try {
            Field instance = entityManager.find(Field.class, id);
            logger.log(Level.INFO, "get successful");
            return instance;
        } catch (RuntimeException re) {
            logger.log(Level.SEVERE, "get failed", re);
            throw re;
        }
    }

    @Transactional(readOnly = true)
    public Long nbUsers() {
        TypedQuery<Long> q = entityManager.createNamedQuery("Field.countAll", Long.class);
        return q.getSingleResult();
    }

    @Transactional(readOnly = true)
    public List<Field> findAll() {
        TypedQuery<Field> q = entityManager.createNamedQuery("Field.findAll", Field.class);
        return q.getResultList();
    }

    @Transactional(readOnly = true)
    public Field findByLabel(String label) {
        logger.log(Level.INFO, "getting Field instance with label: " + label);
        TypedQuery<Field> q = entityManager.createNamedQuery("Field.findByLabel", Field.class);
        q.setParameter("label", label);
        logger.log(Level.INFO, "get successful");
        return q.getSingleResult();

    }

    @Transactional(readOnly = true)
    public Set<Field> findFields(String fields) {
        Set<Field> fieldsSet = new HashSet<Field>();
        System.out.println("fields: " + fields);
        String[] fieldsArray = fields.split(",");
        for (String field : fieldsArray) {
            logger.log(Level.INFO, "getting Field instance with label: " + field);
            TypedQuery<Field> q = entityManager.createNamedQuery("Field.findByLabel", Field.class);
            q.setParameter("label", field);
            fieldsSet.add(q.getSingleResult());
        }
        logger.log(Level.INFO, "get successful");
        return fieldsSet;
    }
}


