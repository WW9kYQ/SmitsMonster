package fr.atlantique.imt.inf211.jobmngt.dao;
// Generated 28 févr. 2025, 21:17:05 by Hibernate Tools 5.6.15.Final


import fr.atlantique.imt.inf211.jobmngt.entity.*;

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
 * Home object for domain model class Joboffer.
 *
 * @author Hibernate Tools
 * @see .Joboffer
 */
@Repository
public class JobOfferDao {

    private static final Logger logger = Logger.getLogger(JobOfferDao.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void persist(JobOffer transientInstance) {
        logger.log(Level.INFO, "persisting Joboffer instance");
        try {
            entityManager.persist(transientInstance);
            logger.log(Level.INFO, "persist successful");
        } catch (RuntimeException re) {
            logger.log(Level.SEVERE, "persist failed", re);
            throw re;
        }
    }

    @Transactional
    public void remove(JobOffer persistentInstance) {
        logger.log(Level.INFO, "removing Joboffer instance");
        try {
            entityManager.remove(persistentInstance);
            logger.log(Level.INFO, "remove successful");
        } catch (RuntimeException re) {
            logger.log(Level.SEVERE, "remove failed", re);
            throw re;
        }
    }

    @Transactional
    public JobOffer merge(JobOffer detachedInstance) {
        logger.log(Level.INFO, "merging Joboffer instance");
        try {
            JobOffer result = entityManager.merge(detachedInstance);
            logger.log(Level.INFO, "merge successful");
            return result;
        } catch (RuntimeException re) {
            logger.log(Level.SEVERE, "merge failed", re);
            throw re;
        }
    }

    @Transactional(readOnly = true)
    public List<JobOffer> findAll() {
        logger.log(Level.INFO, "getting all Joboffer instances");
        try {
            TypedQuery<JobOffer> query = entityManager.createNamedQuery("JobOffer.findAll", JobOffer.class);
            return query.getResultList();
        } catch (RuntimeException re) {
            logger.log(Level.SEVERE, "get all failed", re);
            throw re;
        }
    }

    @Transactional(readOnly = true)
    public JobOffer findById(int id) {
        logger.log(Level.INFO, "getting Joboffer instance with id: " + id);
        try {
            TypedQuery<JobOffer> q = entityManager.createNamedQuery("JobOffer.findById", JobOffer.class);
            q.setParameter("id", id);
            logger.log(Level.INFO, "get successful");
            return q.getSingleResult();
        } catch (RuntimeException re) {
            logger.log(Level.SEVERE, "get failed", re);
            throw re;
        }
    }

    @Transactional(readOnly = true)
    public List<JobOffer> findOffersByCompany(Company company) {
        logger.log(Level.INFO, "getting Joboffer instance by company: " + company);
        try {
            TypedQuery<JobOffer> q = entityManager.createNamedQuery("JobOffer.findByCompany", JobOffer.class);
            q.setParameter("company", company);
            return q.getResultList();
        } catch (RuntimeException re) {
            logger.log(Level.SEVERE, "get failed", re);
            throw re;
        }
    }

    @Transactional(readOnly = true)
    public List<JobOffer> findByFieldAndQualif(Field field, QualificationLevel qualificationLevel) {
        TypedQuery<JobOffer> q = entityManager.createNamedQuery("JobOffer.findByFieldAndQualif", JobOffer.class);
        q.setParameter("field", field);
        q.setParameter("qualificationLevel", qualificationLevel);
        logger.log(Level.INFO, "Number of jpb offers found: " + q.getResultList().size());
        return q.getResultList();
    }

    @Transactional(readOnly = true)
    public List<JobOffer> findByFieldsAndQualif(Set<Field> fields, QualificationLevel qualificationLevel) {
        TypedQuery<JobOffer> q = entityManager.createNamedQuery("JobOffer.findByFieldsAndQualif", JobOffer.class);
        q.setParameter("fields", fields);
        q.setParameter("qualificationlevel", qualificationLevel);
        logger.log(Level.INFO, "Number of jpb offers found: " + q.getResultList().size());
        return q.getResultList();
    }
    //countOfJobOffers
    @Transactional(readOnly = true)
    public Integer countOfJobOffers() {
        TypedQuery<Long> q = entityManager.createNamedQuery("JobOffer.countOfJobOffers", Long.class);
        return q.getSingleResult().intValue();
    }
}

