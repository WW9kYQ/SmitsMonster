package fr.atlantique.imt.inf211.jobmngt.dao;
// Generated 28 févr. 2025, 21:17:05 by Hibernate Tools 5.6.15.Final


import fr.atlantique.imt.inf211.jobmngt.entity.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.persistence.NamedQuery;
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

    @Transactional(readOnly = true)
    public QualificationLevel findById(int id) {
        logger.log(Level.INFO, "getting Qualificationlevel instance with id: " + id);
        try {
            TypedQuery<QualificationLevel> q = entityManager.createNamedQuery("QualificationLevel.findById",QualificationLevel.class);
            q.setParameter("id", id);
            logger.log(Level.INFO, "get successful");
            return q.getSingleResult();
        } catch (RuntimeException re) {
            logger.log(Level.SEVERE, "get failed", re);
            throw re;
        }
    }

    @Transactional(readOnly = true)
    public Long nbLevels() {
        TypedQuery<Long> q = entityManager.createNamedQuery("QualificationLevel.countAll", Long.class);
        return q.getSingleResult();
    }

    @Transactional(readOnly = true)
    public List<QualificationLevel> findAll() {
        TypedQuery<QualificationLevel> q = entityManager.createNamedQuery("QualificationLevel.findAll", QualificationLevel.class);
        List<QualificationLevel> levels = q.getResultList();
        System.out.println("q.getResultList() = " + levels);
        return levels;
    }

    @Transactional(readOnly = true)
    public QualificationLevel findByLabel(String label) {
        logger.log(Level.INFO, "getting QualificationLevel instance with label: " + label);
        TypedQuery<QualificationLevel> q =entityManager.createNamedQuery("QualificationLevel.findByLabel", QualificationLevel.class);
        q.setParameter("label", label);
        logger.log(Level.INFO, "get successful");
        return q.getSingleResult();

    }
}

