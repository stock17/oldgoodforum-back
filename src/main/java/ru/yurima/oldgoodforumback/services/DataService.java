package ru.yurima.oldgoodforumback.services;

import ru.yurima.oldgoodforumback.entities.Topic;
import ru.yurima.oldgoodforumback.repositories.PostRepository;
import ru.yurima.oldgoodforumback.repositories.TopicRepository;
import ru.yurima.oldgoodforumback.repositories.TopicRepositoryHibernateImpl;

import java.util.List;

public class DataService {

    TopicRepository topicRepository;
    PostRepository postRepository;

    public DataService(TopicRepository topicRepository, PostRepository postRepository) {
        this.topicRepository = topicRepository;
        this.postRepository = postRepository;
    }

    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }
}
