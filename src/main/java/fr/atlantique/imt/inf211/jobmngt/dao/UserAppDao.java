package fr.atlantique.imt.inf211.jobmngt.dao;
// Generated Jan 27, 2025, 11:09:47 AM by Hibernate Tools 5.6.15.Final


import fr.atlantique.imt.inf211.jobmngt.entity.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;
import java.util.Optional;
import jakarta.persistence.TypedQuery;
/**
 * Home object for domain model class Userapp.
 * @see .Userapp
 * @author Hibernate Tools
 */
@Repository
public class UserAppDao {

    private static final Logger logger = Logger.getLogger(UserAppDao.class.getName());

    @PersistenceContext private EntityManager entityManager;
    
    public void persist(UserApp transientInstance) {
        logger.log(Level.INFO, "persisting Userapp instance");
        try {
            entityManager.persist(transientInstance);
            logger.log(Level.INFO, "persist successful");
        }
        catch (RuntimeException re) {
            logger.log(Level.SEVERE, "persist failed", re);
            throw re;
        }
    }
    
    public void remove(UserApp persistentInstance) {
        logger.log(Level.INFO, "removing Userapp instance");
        try {
            entityManager.remove(persistentInstance);
            logger.log(Level.INFO, "remove successful");
        }
        catch (RuntimeException re) {
            logger.log(Level.SEVERE, "remove failed", re);
            throw re;
        }
    }
    
    public UserApp merge(UserApp detachedInstance) {
        logger.log(Level.INFO, "merging Userapp instance");
        try {
            UserApp result = entityManager.merge(detachedInstance);
            logger.log(Level.INFO, "merge successful");
            return result;
        }
        catch (RuntimeException re) {
            logger.log(Level.SEVERE, "merge failed", re);
            throw re;
        }
    }
    
    public UserApp findById( int id) {
        logger.log(Level.INFO, "getting Userapp instance with id: " + id);
        try {
            UserApp instance = entityManager.find(UserApp.class, id);
            logger.log(Level.INFO, "get successful");
            return instance;
        }
        catch (RuntimeException re) {
            logger.log(Level.SEVERE, "get failed", re);
            throw re;
        }
    }

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


    public Long nbUsers() {
        String r = "select count(*) from Userapp c";
        Query q = entityManager.createQuery(r);
        return (Long) q.getSingleResult();
    }

    
    public List<UserApp> findAll(String sort, String order) {
        String r = "SELECT s FROM Userapp s ORDER BY s." + sort;
        if (order.equals("asc")) {
            r += " ASC";
        } else {
            r += " DESC";
        }
        Query q = entityManager.createQuery(r);
        return (List<UserApp>) q.getResultList();
    }
}

