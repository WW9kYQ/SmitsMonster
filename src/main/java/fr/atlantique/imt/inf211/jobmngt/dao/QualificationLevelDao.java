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
 * Home object for domain model class Qualificationlevel.
 *
 * @author Hibernate Tools
 * @see .Qualificationlevel
 */
@Repository
public class QualificationLevelDao {

    private static final Logger logger = Logger.getLogger(QualificationLevelDao.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void persist(QualificationLevel transientInstance) {
        logger.log(Level.INFO, "persisting Qualificationlevel instance");
        try {
            entityManager.persist(transientInstance);
            logger.log(Level.INFO, "persist successful");
        } catch (RuntimeException re) {
            logger.log(Level.SEVERE, "persist failed", re);
            throw re;
        }
    }

    @Transactional
    public void remove(QualificationLevel persistentInstance) {
        logger.log(Level.INFO, "removing Qualificationlevel instance");
        try {
            entityManager.remove(persistentInstance);
            logger.log(Level.INFO, "remove successful");
        } catch (RuntimeException re) {
            logger.log(Level.SEVERE, "remove failed", re);
            throw re;
        }
    }

    @Transactional
    public QualificationLevel merge(QualificationLevel detachedInstance) {
        logger.log(Level.INFO, "merging Qualificationlevel instance");
        try {
            QualificationLevel result = entityManager.merge(detachedInstance);
            logger.log(Level.INFO, "merge successful");
            return result;
        } catch (RuntimeException re) {
            logger.log(Level.SEVERE, "merge failed", re);
            throw re;
        }
    }

    @Transactional(readOnly = true)
    public QualificationLevel findById(int id) {
        logger.log(Level.INFO, "getting Qualificationlevel instance with id: " + id);
        try {
            QualificationLevel instance = entityManager.find(QualificationLevel.class, id);
            logger.log(Level.INFO, "get successful");
            return instance;
        } catch (RuntimeException re) {
            logger.log(Level.SEVERE, "get failed", re);
            throw re;
        }
    }

    @Transactional(readOnly = true)
    public Long nbLevels() {
        String r = "select count(*) from QualificationLevel c";
        TypedQuery<Long> q = entityManager.createQuery(r, Long.class);
        return q.getSingleResult();
    }

    @Transactional(readOnly = true)
    public List<QualificationLevel> findAll(String sort, String order) {
        String r = "SELECT s FROM QualificationLevel s ORDER BY s." + sort;
        if (order.equals("asc")) {
            r += " ASC";
        } else {
            r += " DESC";
        }
        TypedQuery<QualificationLevel> q = entityManager.createQuery(r, QualificationLevel.class);
        return q.getResultList();
    }

    @Transactional(readOnly = true)
    //findByLabel
    public List<QualificationLevel> findByLabel(String label){
        logger.log(Level.INFO, "getting QualificationLevel instance with label: " + label);
        List<QualificationLevel> instances = entityManager.createQuery("SELECT q FROM QualificationLevel q WHERE q.label = :label", QualificationLevel.class)
                .setParameter("label", label)
                .getResultList();
        logger.log(Level.INFO, "get successful");
        return instances;

    }
}

