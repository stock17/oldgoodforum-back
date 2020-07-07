package ru.yurima.oldgoodforumback.users;

import ru.yurima.oldgoodforumback.entities.User;

import java.util.List;

public interface UserService {
    public void register(String name, String login, String password);
    public void unregister(String login);
    public void unregister(long id);
    public User findByLogin(String login);
    public User findById(long id);
    public List<User> getAllUsers();
}
