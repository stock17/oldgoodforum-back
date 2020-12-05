package ru.yurima.oldgoodforumback.engine;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.yurima.oldgoodforumback.db.HibernateUtil;
import ru.yurima.oldgoodforumback.entities.Post;
import ru.yurima.oldgoodforumback.entities.Topic;
import ru.yurima.oldgoodforumback.entities.User;
import ru.yurima.oldgoodforumback.repositories.*;
import ru.yurima.oldgoodforumback.services.DataService;
import ru.yurima.oldgoodforumback.servlets.MainServlet;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Engine {
    UserRepository userRepository = new UserRepositoryHibernateImpl();
    TopicRepository topicRepository = new TopicRepositoryHibernateImpl();
    PostRepository postRepository = new PostRepositoryHibernateImpl();
    DataService dataService = new DataService(topicRepository, postRepository);

    public static void main(String[] args) throws Exception {
        new Engine().start();
    }

    public void start() throws Exception {

        init();

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);

        contextHandler.addServlet(new ServletHolder(new MainServlet(dataService)),"/");
        HandlerList handlerList = new HandlerList(contextHandler);

        Server server = new Server(8080);
        server.setHandler(handlerList);
        server.start();
        server.join();
    }

    private void init() {
        final EntityManagerFactory factory = HibernateUtil.getFactory();
//        factory.createEntityManager().persist(new User("1", "2"));
        /*User author1 = new User("username1", "userlogin1", "userpass1");
        User author2 = new User("username2", "userlogin2", "userpass2");
        userRepository.save(author1);
        userRepository.save(author2);

        Topic topic1 = new Topic("TopicTitle1", author1); topicRepository.save(topic1);
        Topic topic2 = new Topic("TopicTitle2", author2); topicRepository.save(topic2);

        Post post1 = new Post(author1, topic1, "Post content1");
        postRepository.save(post1);
        Post post2 = new Post(author2, topic1, "Post content2");
        postRepository.save(post2);

        Post post3 = new Post(author1, topic2, "Post content3");
        postRepository.save(post3);
        Post post4 = new Post(author2, topic2, "Post content1");
        postRepository.save(post4);*/
    }


}
