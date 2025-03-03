package fr.atlantique.imt.inf211.jobmngt.dao;
// Generated 28 févr. 2025, 21:17:05 by Hibernate Tools 5.6.15.Final


import fr.atlantique.imt.inf211.jobmngt.entity.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

/**
 * Home object for domain model class Application.
 *
 * @author Hibernate Tools
 * @see .Application
 */
@Repository
public class ApplicationDao {

    private static final Logger logger = Logger.getLogger(ApplicationDao.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void persist(Application transientInstance) {
        logger.log(Level.INFO, "persisting Application instance");
        try {
            entityManager.persist(transientInstance);
            logger.log(Level.INFO, "persist successful");
        } catch (RuntimeException re) {
            logger.log(Level.SEVERE, "persist failed", re);
            throw re;
        }
    }

    @Transactional
    public void remove(Application persistentInstance) {
        logger.log(Level.INFO, "removing Application instance");
        try {
            entityManager.remove(persistentInstance);
            logger.log(Level.INFO, "remove successful");
        } catch (RuntimeException re) {
            logger.log(Level.SEVERE, "remove failed", re);
            throw re;
        }
    }

    @Transactional
    public Application merge(Application detachedInstance) {
        logger.log(Level.INFO, "merging Application instance");
        try {
            Application result = entityManager.merge(detachedInstance);
            logger.log(Level.INFO, "merge successful");
            return result;
        } catch (RuntimeException re) {
            logger.log(Level.SEVERE, "merge failed", re);
            throw re;
        }
    }

    @Transactional(readOnly = true)
    public Application findById(int id) {
        logger.log(Level.INFO, "getting Application instance with id: " + id);
        try {
            Application instance = entityManager.find(Application.class, id);
            logger.log(Level.INFO, "get successful");
            return instance;
        } catch (RuntimeException re) {
            logger.log(Level.SEVERE, "get failed", re);
            throw re;
        }
    }

    @Transactional(readOnly = true)
    public List<Application> findByFieldAndQualif(Field field, QualificationLevel qualificationLevel) {
        logger.log(Level.INFO, "Recherche des applications pour field: " + field + " et qualification level: " + qualificationLevel);
        List<Application> applications = entityManager.createNamedQuery("Application.findByFieldAndQualif", Application.class)
                .setParameter("field", field)
                .setParameter("qualificationLevel", qualificationLevel)
                .getResultList();

        logger.log(Level.INFO, "Nombre d'applications trouvées : " + applications.size());
        return applications;
    }
}


