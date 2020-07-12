package ru.yurima.oldgoodforumback.topics;

import ru.yurima.oldgoodforumback.db.HibernateUtil;
import ru.yurima.oldgoodforumback.entities.Topic;
import javax.persistence.EntityManager;
import java.util.List;

public class TopicDaoImpl implements TopicDao{
    @Override
    public void create(Topic topic) {
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(topic);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Topic getById(long id) {
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        Topic topic = em.find(Topic.class, id);
        em.getTransaction().commit();
        em.close();
        return topic;
    }

    @Override
    public List<Topic> getAllTopics() {
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        List<Topic> list = em.createQuery("SELECT t FROM Topic t", Topic.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return list;
    }

    @Override
    public void update(Topic topic) {
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        em.merge(topic);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(Topic topic) {
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Topic t WHERE id = :id")
                .setParameter("id", topic.getId())
                .executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void clear() {
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Topic t").executeUpdate();
        em.getTransaction().commit();
        em.close();

    }
}
