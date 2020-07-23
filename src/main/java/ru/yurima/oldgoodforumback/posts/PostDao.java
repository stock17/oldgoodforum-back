package ru.yurima.oldgoodforumback.posts;

import ru.yurima.oldgoodforumback.entities.Post;

public interface PostDao {
    public void save(Post post);
    public Post getById(long id);
    public void update(Post post);
    public void delete(Post post);
    public void clear();
}
