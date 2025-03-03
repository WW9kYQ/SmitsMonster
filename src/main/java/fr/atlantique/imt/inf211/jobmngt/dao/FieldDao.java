package fr.atlantique.imt.inf211.jobmngt.dao;
// Generated 28 févr. 2025, 21:17:05 by Hibernate Tools 5.6.15.Final


import fr.atlantique.imt.inf211.jobmngt.entity.*;

import java.util.List;
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

    @Transactional
    public void persist(Field transientInstance) {
        logger.log(Level.INFO, "persisting Field instance");
        try {
            entityManager.persist(transientInstance);
            logger.log(Level.INFO, "persist successful");
        } catch (RuntimeException re) {
            logger.log(Level.SEVERE, "persist failed", re);
            throw re;
        }
    }

    @Transactional
    public void remove(Field persistentInstance) {
        logger.log(Level.INFO, "removing Field instance");
        try {
            entityManager.remove(persistentInstance);
            logger.log(Level.INFO, "remove successful");
        } catch (RuntimeException re) {
            logger.log(Level.SEVERE, "remove failed", re);
            throw re;
        }
    }

    @Transactional
    public Field merge(Field detachedInstance) {
        logger.log(Level.INFO, "merging Field instance");
        try {
            Field result = entityManager.merge(detachedInstance);
            logger.log(Level.INFO, "merge successful");
            return result;
        } catch (RuntimeException re) {
            logger.log(Level.SEVERE, "merge failed", re);
            throw re;
        }
    }

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
        String r = "select count(*) from Field c";
        TypedQuery<Long> q = entityManager.createQuery(r, Long.class);
        return q.getSingleResult();
    }

    @Transactional(readOnly = true)
    public List<Field> findAll(String sort, String order) {
        String r = "SELECT s FROM Field s ORDER BY s." + sort;
        if (order.equals("asc")) {
            r += " ASC";
        } else {
            r += " DESC";
        }
        TypedQuery<Field> q = entityManager.createQuery(r, Field.class);
        return q.getResultList();
    }
    @Transactional(readOnly = true)
    //Ajout d’une méthode pour l’obtention de la liste des secteurs dont le label est donné
    //findByLabel
    public List<Field> findByLabel(String label){
        logger.log(Level.INFO, "getting Field instance with label: " + label);
        List<Field> instances = entityManager.createQuery("SELECT f FROM Field f WHERE f.label = :label", Field.class)
                .setParameter("label", label)
                .getResultList();
        logger.log(Level.INFO, "get successful");
        return instances;

    }
}


