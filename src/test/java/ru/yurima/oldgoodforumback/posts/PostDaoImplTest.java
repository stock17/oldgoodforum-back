package ru.yurima.oldgoodforumback.posts;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yurima.oldgoodforumback.db.HibernateUtil;
import ru.yurima.oldgoodforumback.entities.Post;
import ru.yurima.oldgoodforumback.entities.Topic;
import ru.yurima.oldgoodforumback.entities.User;
import ru.yurima.oldgoodforumback.topics.TopicDaoImpl;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

public class PostDaoImplTest {

    private PostDao postDao;
    private User author;
    private Topic topic;
    private Post post;

    @Before
    public void init(){
        postDao = new PostDaoImpl();
        author = new User("username", "login", "password");
        topic = new Topic("Topic Title", author);
        post = new Post("Post Content", author, topic);

        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(author);
        em.getTransaction().commit();
        em.close();
    }

    @After
    public void destroy(){
        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM User u");
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void testSave() {
        int size = topic.getPosts().size();
        Post newPost = new Post("Post Content 2", author, topic);
        postDao.save(newPost);
        assertEquals(size + 1, topic.getPosts().size());
        assert(topic.getPosts().contains(newPost));
    }

    @Test
    public void testGetById() {
        Post actual = postDao.getById(post.getId());
        assertEquals(post, actual);
    }

    @Test
    public void testUpdate() {
        String formerContent = post.getContent();
        String latterContent = "Edited content";
        post.setContent(latterContent);
        postDao.update(post);

        System.out.println(topic.getPosts());

        Post actual = postDao.getById(post.getId());
        assertNotEquals(formerContent, actual.getContent());
        assertEquals(latterContent, actual.getContent());
    }

    @Test
    public void testDelete() {
        System.out.println(topic.getPosts());
        System.out.println(post);
        post.setTopic(null);
        postDao.delete(post);
        System.out.println(topic.getPosts()); // 1. Post is not deleted from memory

        System.out.println(post);
        Post post1 = postDao.getById(post.getId()); //NULL. Post is deleted from persistence
        assertEquals(0, topic.getPosts().size());
    }
}