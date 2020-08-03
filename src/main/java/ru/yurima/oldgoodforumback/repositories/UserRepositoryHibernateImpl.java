package ru.yurima.oldgoodforumback.repositories;

import ru.yurima.oldgoodforumback.db.HibernateUtil;
import ru.yurima.oldgoodforumback.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

public class UserRepositoryHibernateImpl implements UserRepository {
    @Override
    public void save(User user) {
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(user);
        tx.commit();
        em.close();
    }

    @Override
    public Optional<User> findById(long id) {
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        User user = em.find(User.class, id);
        em.getTransaction().commit();
        em.close();
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        try {
        User user = (User) em.createQuery("SELECT u FROM User u WHERE u.login = :login")
                .setParameter("login", login)
                .getSingleResult();
            return Optional.ofNullable(user);
        } catch (NoResultException e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            em.getTransaction().commit();
            em.close();
        }
    }

    @Override
    public List<User> findAll() {
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        List<User> users = em.createQuery("SELECT u FROM User u", User.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return users;
    }

    @Override
    public void delete(User user) {
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        User deletedUser = em.find(User.class, user.getId());
        em.remove(deletedUser);
        tx.commit();
        em.close();
    }

    @Override
    public void deleteById(long id) {
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        User deletedUser = em.find(User.class, id);
        em.remove(deletedUser);
        tx.commit();
        em.close();
    }

    @Override
    public void deleteAll() {
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM User u").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
}
