package ru.yurima.oldgoodforumback.entities;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.Assert.*;

public class TopicTest {

    EntityManager em;
    User author;
    String title;

    @Before
    public void init(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ru.yurima.oldgoodforumback.entities");
        em = emf.createEntityManager();
        String[] userData1 = new String[]{"John Snow", "John133", "123456"};
        author = new User(userData1[0],userData1[1], userData1[2]);
        title = "New topic 111";
    }

    @Test
    public void testTopicPersistence(){

        EntityTransaction tx =  em.getTransaction();
        tx.begin();

        Topic topic = new Topic(title, author);
        em.persist(author);
        em.persist(topic);
        tx.commit();

        tx =  em.getTransaction();
        tx.begin();
        List<Topic> topics = em.createQuery("SELECT t FROM Topic t", Topic.class).getResultList();
        tx.commit();
        for (Topic t : topics) {
            System.out.println(t);
        }

        assertEquals(title, topics.get(0).getTitle());
        assertEquals(author, topics.get(0).getAuthor());
        assert(author.getTopics().contains(topics.get(0)));
    }

    @After
    public void destroy(){
        em.close();
    }

}