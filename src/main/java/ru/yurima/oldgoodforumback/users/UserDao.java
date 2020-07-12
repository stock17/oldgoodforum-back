package ru.yurima.oldgoodforumback.users;

import ru.yurima.oldgoodforumback.entities.User;

import java.util.List;

public interface UserDao {
    public void save(User user);
    public void deleteByLogin(String login);
    public void deleteById(long id);
    public User findByLogin(String login);
    public User findById(long id);
    public List<User> getAllUsers();
    public void clear();
}
