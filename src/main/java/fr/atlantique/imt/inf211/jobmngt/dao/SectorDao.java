package fr.atlantique.imt.inf211.jobmngt.dao;
// Generated Jan 27, 2025, 11:09:47 AM by Hibernate Tools 5.6.15.Final


import fr.atlantique.imt.inf211.jobmngt.entity.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.persistence.Query;

import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * Home object for domain model class Sector.
 * @see .Sector
 * @author Hibernate Tools
 */
@Repository
public class SectorDao {

    private static final Logger logger = Logger.getLogger(SectorDao.class.getName());

    @PersistenceContext private EntityManager entityManager;
    
    public void persist(Field transientInstance) {
        logger.log(Level.INFO, "persisting Sector instance");
        try {
            entityManager.persist(transientInstance);
            logger.log(Level.INFO, "persist successful");
        }
        catch (RuntimeException re) {
            logger.log(Level.SEVERE, "persist failed", re);
            throw re;
        }
    }
    
    public void remove(Field persistentInstance) {
        logger.log(Level.INFO, "removing Sector instance");
        try {
            entityManager.remove(persistentInstance);
            logger.log(Level.INFO, "remove successful");
        }
        catch (RuntimeException re) {
            logger.log(Level.SEVERE, "remove failed", re);
            throw re;
        }
    }
    
    public Field merge(Field detachedInstance) {
        logger.log(Level.INFO, "merging Sector instance");
        try {
            Field result = entityManager.merge(detachedInstance);
            logger.log(Level.INFO, "merge successful");
            return result;
        }
        catch (RuntimeException re) {
            logger.log(Level.SEVERE, "merge failed", re);
            throw re;
        }
    }
    
    public Field findById(int id) {
        logger.log(Level.INFO, "getting Sector instance with id: " + id);
        try {
            Field instance = entityManager.find(Field.class, id);
            logger.log(Level.INFO, "get successful");
            return instance;
        }
        catch (RuntimeException re) {
            logger.log(Level.SEVERE, "get failed", re);
            throw re;
        }
    }



    public List<Field> findAll(String sort, String order) {
        String r = "SELECT s FROM Field s ORDER BY s." + sort;
        if (order.toLowerCase().equals("asc")) {
            r += " ASC";
        } else {
            r += " DESC";
        }
        Query q = entityManager.createQuery(r);
        return (List<Field>) q.getResultList();
    }
}

