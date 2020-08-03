package ru.yurima.oldgoodforumback.topics;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yurima.oldgoodforumback.db.HibernateUtil;
import ru.yurima.oldgoodforumback.entities.Topic;
import ru.yurima.oldgoodforumback.entities.User;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.Assert.*;

public class TopicDaoImplTest {

    private TopicDao topicDao;
    private User author;

    @Before
    public void init(){
        topicDao = new TopicDaoImpl();
        author = new User("username", "login", "password");
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(author);
        em.getTransaction().commit();
        em.close();
    }

    @After
    public void destroy(){
        topicDao.clear();
        List<Topic> list1 = topicDao.getAllTopics();
        for(Topic t : list1) System.out.println(t);
    }

    @Test
    public void create() {
        Topic topic = new Topic("Title_create_id", author);
        topicDao.save(topic);
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        List<Topic> list = em.createQuery("SELECT t FROM Topic t", Topic.class).getResultList();
        em.close();
        assertEquals(1, list.size());
        assertEquals(topic, list.get(0));
    }

    @Test
    public void getById() {
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        Topic expected = new Topic("Title", author);
        em.persist(expected);
        em.getTransaction().commit();
        em.close();

        Topic actual = topicDao.getById(expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    public void getAllTopics() {
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(new Topic("Title1", author));
        em.persist(new Topic("Title2", author));
        em.persist(new Topic("Title3", author));
        em.getTransaction().commit();
        em.close();
        List<Topic> list = topicDao.getAllTopics();
        for(Topic t : list) System.out.println(t);
        assertEquals(3, list.size());
    }

    @Test
    public void update() {
        String oldTitle = "old_title";
        String newTitle = "new_title";
        Topic topic = new Topic(oldTitle, author);
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(topic);
        em.getTransaction().commit();
        em.close();
        System.out.println(topic);

        em = HibernateUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        Topic topic1 = em.createQuery("SELECT t FROM Topic t WHERE t.id = :id", Topic.class)
                .setParameter("id", topic.getId()).getSingleResult();
        topic1.setTitle(newTitle);
        em.getTransaction().commit();
        em.close();

        em = HibernateUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        Topic topic2 = em.createQuery("SELECT t FROM Topic t WHERE t.id = :id", Topic.class)
                .setParameter("id", topic.getId()).getSingleResult();
        em.getTransaction().commit();
        em.close();
        System.out.println(topic2);
        assertEquals(newTitle, topic2.getTitle());
    }

    @Test
    public void delete() {
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        Topic topic = new Topic("Title", author);
        em.persist(topic);
        em.getTransaction().commit();
        em.close();

        topic.getAuthor().removeTopic(topic);
        topicDao.delete(topic);
        em = HibernateUtil.getFactory().createEntityManager();
        List<Topic> list = em.createQuery("SELECT t FROM Topic t", Topic.class).getResultList();
        em.close();
        assertEquals(0, list.size());
    }
}