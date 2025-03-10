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
 * Home object for domain model class Candidate.
 *
 * @author Hibernate Tools
 * @see .Candidate
 */
@Repository
public class CandidateDao {

    private static final Logger logger = Logger.getLogger(CandidateDao.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void persist(Candidate transientInstance) {
        logger.log(Level.INFO, "persisting Candidate instance");
        try {
            entityManager.persist(transientInstance);
            logger.log(Level.INFO, "persist successful");
        } catch (RuntimeException re) {
            logger.log(Level.SEVERE, "persist failed", re);
            throw re;
        }
    }

    @Transactional
    public void remove(Candidate persistentInstance) {
        logger.log(Level.INFO, "removing Candidate instance");
        try {
            entityManager.remove(persistentInstance);
            logger.log(Level.INFO, "remove successful");
        } catch (RuntimeException re) {
            logger.log(Level.SEVERE, "remove failed", re);
            throw re;
        }
    }

    @Transactional
    public Candidate merge(Candidate detachedInstance) {
        logger.log(Level.INFO, "merging Candidate instance");
        try {
            Candidate result = entityManager.merge(detachedInstance);
            logger.log(Level.INFO, "merge successful");
            return result;
        } catch (RuntimeException re) {
            logger.log(Level.SEVERE, "merge failed", re);
            throw re;
        }
    }

    @Transactional(readOnly = true)
    public Integer count() {
        logger.log(Level.INFO, "getting Candidate count");
        try {
            String hql = "SELECT COUNT(*) FROM Candidate";
            return ((Long) entityManager.createQuery(hql).getSingleResult()).intValue();
        } catch (RuntimeException re) {
            logger.log(Level.SEVERE, "get count failed", re);
            throw re;
        }
    }

    @Transactional(readOnly = true)
    public Candidate findById(String id) {
        logger.log(Level.INFO, "getting Candidate instance with id: " + id);
        try {
            Candidate instance = entityManager.find(Candidate.class, id);
            logger.log(Level.INFO, "get successful");
            return instance;
        } catch (RuntimeException re) {
            logger.log(Level.SEVERE, "get failed", re);
            throw re;
        }
    }

    //findAll
    @Transactional(readOnly = true)
    public List<Candidate> findAll() {
        logger.log(Level.INFO, "getting all Candidate instances");
        try {
            String hql = "FROM Candidate";
            return entityManager.createQuery(hql).getResultList();
        } catch (RuntimeException re) {
            logger.log(Level.SEVERE, "get all failed", re);
            throw re;
        }
    }


    //findByMail
    @Transactional(readOnly = true)
    public Candidate findByMail(String mail) {
        logger.log(Level.INFO, "getting Candidate instance with mail: " + mail);
        try {
            String hql = "FROM Candidate WHERE mail = :mail";
            return (Candidate) entityManager.createQuery(hql).setParameter("mail", mail).getSingleResult();
        } catch (RuntimeException re) {
            logger.log(Level.SEVERE, "get by mail failed", re);
            throw re;
        }
    }
}

