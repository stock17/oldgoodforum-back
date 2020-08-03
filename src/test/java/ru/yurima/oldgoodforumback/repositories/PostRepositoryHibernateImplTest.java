package ru.yurima.oldgoodforumback.repositories;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yurima.oldgoodforumback.db.HibernateUtil;
import ru.yurima.oldgoodforumback.entities.Post;
import ru.yurima.oldgoodforumback.entities.Topic;
import ru.yurima.oldgoodforumback.entities.User;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

public class PostRepositoryHibernateImplTest {

    private static final TopicRepository topicRepository = new TopicRepositoryHibernateImpl();
    private static final UserRepository userRepository = new UserRepositoryHibernateImpl();
    private static final PostRepository postRepository = new PostRepositoryHibernateImpl();

    private static User author;
    private static final String name = "John Smith";
    private static final String login = "johnny123";
    private static final String password = "12345678";

    private static Topic topic;
    private static final String title = "Title";

    private static final String content =
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the" +
            "industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and" +
            "scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap " +
            "into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the" +
            " release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing " +
            "software like Aldus PageMaker including versions of Lorem Ipsum.";

    private static final long NO_SUCH_ID = Long.MAX_VALUE;

    @Before
    public void setUp() throws Exception {
        User author = new User(name, login, password);
        userRepository.save(author);
        Topic topic = new Topic(title, author);
        topicRepository.save(topic);
    }

    @After
    public void tearDown() throws Exception {
        userRepository.deleteAll();
    }

    @Test
    public void save() {
        Post post = new Post(author, topic, content);
        postRepository.save(post);
        assertEquals(1, userRepository.findAll().size());
    }

    @Test
    public void findById() {
        Post post = new Post(author, topic, content);
        postRepository.save(post);
        long id = post.getId();

        assertTrue(postRepository.findById(id).isPresent());
        Post savedPost = postRepository.findById(id).get();
        assertEquals(author, savedPost.getAuthor());
        assertEquals(topic, savedPost.getTopic());

        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        User savedAuthor = em.find(User.class, author.getId());
        Topic savedTopic = em.find(Topic.class, topic.getId());
        assertEquals(savedAuthor, savedPost.getAuthor());
        assertEquals(savedTopic, savedPost.getTopic());
        em.close();
    }

    @Test
    public void findAll() {
        postRepository.save(new Post(author, topic, content));
        postRepository.save(new Post(author, topic, content));
        postRepository.save(new Post(author, topic, content));
        assertEquals(3, postRepository.findAll().size());
    }

    @Test
    public void delete() {
        Post post = new Post(author, topic, content);
        postRepository.save(post);

        post.unSetAuthor();
        post.unSetTopic();
        postRepository.delete(post);

        assertEquals(0, postRepository.findAll().size());

        assertFalse(author.getPosts().contains(post));
        assertFalse(topic.getPosts().contains(post));

        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        User savedAuthor = em.find(User.class, author.getId());
        Topic savedTopic = em.find(Topic.class, topic.getId());
        assertFalse(savedAuthor.getPosts().contains(post));
        assertFalse(savedTopic.getPosts().contains(post));
        em.close();
    }

    @Test
    public void deleteById() {
        Post post = new Post(author, topic, content);
        postRepository.save(post);

        post.unSetAuthor();
        post.unSetTopic();
        postRepository.deleteById(post.getId());

        assertEquals(0, postRepository.findAll().size());

        assertFalse(author.getPosts().contains(post));
        assertFalse(topic.getPosts().contains(post));

        EntityManager em = HibernateUtil.getFactory().createEntityManager();
        User savedAuthor = em.find(User.class, author.getId());
        Topic savedTopic = em.find(Topic.class, topic.getId());
        assertFalse(savedAuthor.getPosts().contains(post));
        assertFalse(savedTopic.getPosts().contains(post));
        em.close();
    }

    @Test
    public void deleteAll() {
        postRepository.save(new Post(author, topic, content));
        postRepository.save(new Post(author, topic, content));
        postRepository.save(new Post(author, topic, content));

        postRepository.deleteAll();
        assertEquals(0, postRepository.findAll().size());
    }
}