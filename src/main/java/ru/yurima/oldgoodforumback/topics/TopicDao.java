package ru.yurima.oldgoodforumback.topics;

import ru.yurima.oldgoodforumback.entities.Topic;

import java.util.List;

public interface TopicDao {
    public void create(Topic topic);
    public Topic getById(long id);
    public List<Topic> getAllTopics();
    public void update(Topic topic);
    public void delete(Topic topic);
    public void clear();
}
