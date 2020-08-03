package ru.yurima.oldgoodforumback.repositories;

import ru.yurima.oldgoodforumback.db.HibernateUtil;
import ru.yurima.oldgoodforumback.entities.Topic;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class TopicRepositoryHibernateImpl implements TopicRepository{
    @Override
    public void save(Topic topic) {
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(topic);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Optional<Topic> findById(long id) {
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        Topic topic = em.find(Topic.class, id);
        em.getTransaction().commit();
        em.close();
        return Optional.ofNullable(topic);
    }

    @Override
    public List<Topic> findAll() {
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        List<Topic> topics = em.createQuery("SELECT t FROM Topic t", Topic.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return topics;
    }

    @Override
    public void delete(Topic topic) {
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        Topic deletedTopic = em.find(Topic.class, topic.getId());
        em.remove(deletedTopic);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void deleteById(long id) {
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        Topic deletedTopic = em.find(Topic.class, id);
        em.remove(deletedTopic);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void deleteAll() {
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Topic t").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
}
