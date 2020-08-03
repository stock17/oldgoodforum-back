package ru.yurima.oldgoodforumback.repositories;

import ru.yurima.oldgoodforumback.db.HibernateUtil;
import ru.yurima.oldgoodforumback.entities.Post;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class PostRepositoryHibernateImpl implements PostRepository{
    @Override
    public void save(Post post) {
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(post);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Optional<Post> findById(long id) {
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        Post post = em.find(Post.class, id);
        em.getTransaction().commit();
        em.close();
        return Optional.ofNullable(post);
    }

    @Override
    public List<Post> findAll() {
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        List<Post> posts = em.createQuery("SELECT Post p FROM Post", Post.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return posts;
    }

    @Override
    public void delete(Post post) {
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        Post deletedPost = em.find(Post.class, post.getId());
        em.remove(deletedPost);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void deleteById(long id) {
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        Post deletedPost = em.find(Post.class, id);
        em.remove(deletedPost);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void deleteAll() {
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Post").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
}
