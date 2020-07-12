package ru.yurima.oldgoodforumback.users;

import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.hibernate.query.criteria.internal.CriteriaQueryImpl;
import ru.yurima.oldgoodforumback.db.HibernateUtil;
import ru.yurima.oldgoodforumback.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class UserServiceImpl implements UserService{
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
        em.fi
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        em.createQuery("DELETE u FROM User WHERE u.login := " + login);

        tx.commit();
        em.close();
    }

    @Override
    public void unregister(long id) {

    }

    @Override
    public User findByLogin(String login) {
        return null;
    }

    @Override
    public User findById(long id) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }
}
