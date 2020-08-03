package ru.yurima.oldgoodforumback.repositories;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yurima.oldgoodforumback.db.HibernateUtil;
import ru.yurima.oldgoodforumback.entities.Topic;
import ru.yurima.oldgoodforumback.entities.User;

import javax.persistence.EntityManager;

import java.util.Optional;

import static org.junit.Assert.*;

public class TopicRepositoryHibernateImplTest {

    private static final TopicRepository topicRepository = new TopicRepositoryHibernateImpl();
    private static final UserRepository userRepository = new UserRepositoryHibernateImpl();

    private static final String name = "John Smith";
    private static final String login = "johnny123";
    private static final String password = "12345678";

    private static User author;
    private static String title = "Title";
    private static final long NO_SUCH_ID = Long.MAX_VALUE;



    @Before
    public void setUp() throws Exception {
        author = new User(name, login, password);
        userRepository.save(author);
    }

    @After
    public void tearDown() throws Exception {
        topicRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void save() {
        Topic topic = new Topic(title, author);
        topicRepository.save(topic);
        assertEquals(1, topicRepository.findAll().size());
        assertEquals(1, author.getTopics().size());

        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        User savedAuthor = em.find(User.class, author.getId());
        assertEquals(1, savedAuthor.getTopics().size());
        em.close();
    }

    @Test
    public void findById() {
        Topic topic = new Topic(title, author);
        topicRepository.save(topic);
        Optional<Topic> optional = topicRepository.findById(topic.getId());
        assertTrue(optional.isPresent());
        assertEquals(topic, optional.get());
        Optional<Topic> noSuchEntity = topicRepository.findById(NO_SUCH_ID);
        assertFalse(noSuchEntity.isPresent());
    }

    @Test
    public void findAll() {
        topicRepository.save(new Topic(title, author));
        assertEquals(1, topicRepository.findAll().size());
        topicRepository.save(new Topic(title, author));
        assertEquals(2, topicRepository.findAll().size());
    }

    @Test
    public void delete() {
        Topic topic = new Topic(title, author);
        topicRepository.save(topic);

        topic.unSetAuthor();
        topicRepository.delete(topic);
        assertEquals(0, topicRepository.findAll().size());
        assertFalse(author.getTopics().contains(topic));

        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        User savedAuthor = em.find(User.class, author.getId());
        assertFalse(savedAuthor.getTopics().contains(topic));
        em.close();

    }

    @Test
    public void deleteById() {
        Topic topic = new Topic(title, author);
        topicRepository.save(topic);
        long id = topic.getId();

        topic.unSetAuthor();
        topicRepository.deleteById(id);
        assertEquals(0, topicRepository.findAll().size());
        assertFalse(author.getTopics().contains(topic));

        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        User savedAuthor = em.find(User.class, author.getId());
        assertFalse(savedAuthor.getTopics().contains(topic));
        em.close();
    }

    @Test
    public void deleteAll() {
        topicRepository.save(new Topic(title, author));
        topicRepository.save(new Topic(title, author));
        topicRepository.save(new Topic(title, author));
        assertEquals(3, topicRepository.findAll().size());

        topicRepository.deleteAll();
        assertEquals(0, topicRepository.findAll().size());
    }
}