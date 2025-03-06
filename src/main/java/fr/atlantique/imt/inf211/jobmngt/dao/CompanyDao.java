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
 * Home object for domain model class Company.
 *
 * @author Hibernate Tools
 * @see .Company
 */
@Repository
public class CompanyDao {

    private static final Logger logger = Logger.getLogger(CompanyDao.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void persist(Company transientInstance) {
        logger.log(Level.INFO, "persisting Company instance");
        try {
            entityManager.persist(transientInstance);
            logger.log(Level.INFO, "persist successful");
        } catch (RuntimeException re) {
            logger.log(Level.SEVERE, "persist failed", re);
            throw re;
        }
    }

    @Transactional
    public void remove(Company persistentInstance) {
        logger.log(Level.INFO, "removing Company instance");
        try {
            entityManager.remove(persistentInstance);
            logger.log(Level.INFO, "remove successful");
        } catch (RuntimeException re) {
            logger.log(Level.SEVERE, "remove failed", re);
            throw re;
        }
    }

    @Transactional
    public Company merge(Company detachedInstance) {
        logger.log(Level.INFO, "merging Company instance");
        try {
            Company result = entityManager.merge(detachedInstance);
            logger.log(Level.INFO, "merge successful");
            return result;
        } catch (RuntimeException re) {
            logger.log(Level.SEVERE, "merge failed", re);
            throw re;
        }
    }

    @Transactional(readOnly = true)
    public Company findByMail(String mail) {
        logger.log(Level.INFO, "getting Company instance with mail: " + mail);
        try {
            TypedQuery<Company> q = entityManager.createNamedQuery("Company.findByMail", Company.class);
            q.setParameter("mail", mail);
            logger.log(Level.INFO, "get successful");
            return q.getSingleResult();
        } catch (RuntimeException re) {
            logger.log(Level.SEVERE, "get failed", re);
            throw re;
        }
    }

    @Transactional(readOnly = true)
    public Company findByDenomination(String denomination) {
        logger.log(Level.INFO, "getting Company instance with denomination: " + denomination);
        try {
            TypedQuery<Company> q = entityManager.createNamedQuery("Company.findByDenomination", Company.class);
            q.setParameter("denomination", denomination);
            logger.log(Level.INFO, "get successful");
            return q.getSingleResult();
        } catch (RuntimeException re) {
            logger.log(Level.SEVERE, "get failed", re);
            throw re;
        }
    }

    @Transactional(readOnly = true)
    public List<Company> findAll() {
        logger.log(Level.INFO, "getting all Company instances");
        try {
            TypedQuery<Company> q = entityManager.createNamedQuery("Company.findAll", Company.class);
            logger.log(Level.INFO, "get successful");
            return q.getResultList();
        } catch (RuntimeException re) {
            logger.log(Level.SEVERE, "get failed", re);
            throw re;
        }
    }

    //removeCompany
    @Transactional
    public void removeCompany(String denomination) {
        logger.log(Level.INFO, "removing Company instance with denomination: " + denomination);
        try {
            Company company = findByDenomination(denomination);
            entityManager.remove(company);
            logger.log(Level.INFO, "remove successful");
        } catch (RuntimeException re) {
            logger.log(Level.SEVERE, "remove failed", re);
            throw re;
        }
    }
}

