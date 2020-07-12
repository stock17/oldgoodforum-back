package ru.yurima.oldgoodforumback.users;

import ru.yurima.oldgoodforumback.db.HibernateUtil;
import ru.yurima.oldgoodforumback.entities.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public void register(User user) {
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(user);
        tx.commit();
        em.close();
    }

    @Override
    public void unregister(String login) {
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.createQuery("DELETE u FROM User u WHERE u.login = :login").setParameter("login", login);
        tx.commit();
        em.close();
    }

    @Override
    public void unregister(long id) {
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.createQuery("DELETE u FROM User u WHERE u.id = :id").setParameter("id", id);
        tx.commit();
        em.close();
    }

    @Override
    public User findByLogin(String login) {
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        User user = (User) em.createQuery("SELECT u FROM User u WHERE u.login = :login")
                .setParameter("login", login)
                .getSingleResult();
        em.getTransaction().commit();
        em.close();
        return user;
    }

    @Override
    public User findById(long id) {
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        User user = em.find(User.class, id);
        em.getTransaction().commit();
        em.close();
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        List<User> users = em.createQuery("SELECT u FROM User u", User.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return users;
    }
}
