package ru.yurima.oldgoodforumback.entities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.Assert.*;

public class PostTest {
    EntityManager em;
    User author;
    Topic topic;
    String title;
    String content;

    @Before
    public void init() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ru.yurima.oldgoodforumback.entities");
        em = emf.createEntityManager();
        String[] userData1 = new String[]{"John Snow", "John133", "123456"};
        author = new User(userData1[0], userData1[1], userData1[2]);
        title = "New topic 111";
        topic = new Topic(title, author);
        content = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the" +
                "industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and" +
                "scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap " +
                "into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the" +
                " release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing " +
                "software like Aldus PageMaker including versions of Lorem Ipsum.";

    }

    @Test
    public void testTopicPersistence() {

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Post post1 = new Post(author, topic, content);

        em.persist(author);
        em.persist(topic);
        em.persist(post1);
        tx.commit();

        tx = em.getTransaction();
        tx.begin();
        List<Post> posts = em.createQuery("SELECT p FROM Post p", Post.class).getResultList();
        tx.commit();
        for (Post p : posts) {
            System.out.println(p);
        }

        assertEquals(content, posts.get(0).getContent());
        assertEquals(author, posts.get(0).getAuthor());
        assert (topic.getPosts().contains(posts.get(0)));
        assert(author.getPosts().contains(posts.get(0)));
    }

    @After
    public void destroy() {
        em.close();
    }

}