package ru.yurima.oldgoodforumback.users;

import ru.yurima.oldgoodforumback.entities.User;

import java.util.List;

public interface UserDao {
    public void register(User user);
    public void unregister(String login);
    public void unregister(long id);
    public User findByLogin(String login);
    public User findById(long id);
    public List<User> getAllUsers();
}
