package ru.yurima.oldgoodforumback.posts;

import org.hibernate.Hibernate;
import ru.yurima.oldgoodforumback.db.HibernateUtil;
import ru.yurima.oldgoodforumback.entities.Post;

import javax.persistence.EntityManager;

public class PostDaoImpl implements PostDao{
    @Override
    public void save(Post post) {
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(post);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Post getById(long id) {
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        Post post = em.find(Post.class, id);
        em.getTransaction().commit();
        em.close();
        return post;
    }

    @Override
    public void update(Post post) {
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        em.merge(post);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(Post post) {
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Post p WHERE p.id = :id")
            .setParameter("id", post.getId()).executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void clear() {
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Post p").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
}
