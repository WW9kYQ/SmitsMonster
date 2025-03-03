package fr.atlantique.imt.inf211.jobmngt.dao;
// Generated 28 févr. 2025, 21:17:05 by Hibernate Tools 5.6.15.Final


import fr.atlantique.imt.inf211.jobmngt.entity.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * Home object for domain model class Userapp.
 *
 * @author Hibernate Tools
 * @see .Userapp
 */
@Repository
public class UserAppDao {

    private static final Logger logger = Logger.getLogger(UserAppDao.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void persist(UserApp transientInstance) {
        logger.log(Level.INFO, "persisting Userapp instance");
        try {
            entityManager.persist(transientInstance);
            logger.log(Level.INFO, "persist successful");
        } catch (RuntimeException re) {
            logger.log(Level.SEVERE, "persist failed", re);
            throw re;
        }
    }

    @Transactional
    public void remove(UserApp persistentInstance) {
        logger.log(Level.INFO, "removing Userapp instance");
        try {
            entityManager.remove(persistentInstance);
            logger.log(Level.INFO, "remove successful");
        } catch (RuntimeException re) {
            logger.log(Level.SEVERE, "remove failed", re);
            throw re;
        }
    }

    @Transactional
    public UserApp merge(UserApp detachedInstance) {
        logger.log(Level.INFO, "merging Userapp instance");
        try {
            UserApp result = entityManager.merge(detachedInstance);
            logger.log(Level.INFO, "merge successful");
            return result;
        } catch (RuntimeException re) {
            logger.log(Level.SEVERE, "merge failed", re);
            throw re;
        }
    }

    @Transactional(readOnly = true)
    public UserApp findById(Integer id) {
        logger.log(Level.INFO, "getting Userapp instance with id: " + id);
        try {
            UserApp instance = entityManager.find(UserApp.class, id);
            logger.log(Level.INFO, "get successful");
            return instance;
        } catch (RuntimeException re) {
            logger.log(Level.SEVERE, "get failed", re);
            throw re;
        }
    }

    @Transactional(readOnly = true)
    public Long nbUsers() {
        String r = "select count(*) from UserApp c";
        TypedQuery<Long> q = entityManager.createQuery(r, Long.class);
        return q.getSingleResult();
    }

    @Transactional(readOnly = true)
    public List<UserApp> findAll(String sort, String order) {
        String r = "SELECT s FROM UserApp s ORDER BY s." + sort;
        if (order.equals("asc")) {
            r += " ASC";
        } else {
            r += " DESC";
        }
        TypedQuery<UserApp> q = entityManager.createQuery(r, UserApp.class);
        return q.getResultList();
    }

    @Transactional(readOnly = true)
    public Optional<UserApp> checkLogin(UserApp user) {
        String r = "SELECT s FROM UserApp s WHERE s.mail = :login AND s.password = :pwd";
        TypedQuery<UserApp> q = entityManager.createQuery(r, UserApp.class);
        q.setParameter("login", user.getMail());
        q.setParameter("pwd", user.getPassword());
        if (q.getResultList().size() == 0) {
            return Optional.empty();
        }
        return Optional.of(q.getResultList().get(0));
    }

    @Transactional(readOnly = true)
    public Optional<UserApp> findByMail(String mail) {
        String r = "SELECT s FROM UserApp s WHERE s.mail = :mail";
        TypedQuery<UserApp> q = entityManager.createQuery(r, UserApp.class);
        q.setParameter("mail", mail);
        List<UserApp> res = q.getResultList();
        if (res.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(res.get(0));
    }
}

