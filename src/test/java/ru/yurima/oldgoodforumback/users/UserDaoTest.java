package ru.yurima.oldgoodforumback.users;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yurima.oldgoodforumback.entities.User;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserDaoTest {

    UserDao userDao;

    @Before
    public void init() {
        userDao = new UserDaoImpl();
    }

    @After
    public void destroy(){
        userDao.clear();
    }

    @Test
    public void testRegister() {
        User expected = new User("username", "login", "password");
        userDao.save(expected);
        List<User> users = userDao.getAllUsers();
        assert(users.size() == 1);
        assertEquals(expected, users.get(0));
    }

    @Test
    public void testDeleteByLogin() {
        userDao.save(new User("username", "login", "password"));
        List<User> users = userDao.getAllUsers();
        assert(users.size() == 1);

        userDao.deleteByLogin("login");
        users = userDao.getAllUsers();
        assert(users.size() == 0);
    }

    @Test
    public void testDeleteById() {
        userDao.save(new User("username", "login", "password"));
        List<User> users = userDao.getAllUsers();
        assert(users.size() == 1);

        long id = users.get(0).getId();

        userDao.deleteById(id);
        users = userDao.getAllUsers();
        assert(users.size() == 0);
    }

    @Test
    public void findByLogin() {
        User expected = new User("username", "login", "password");
        userDao.save(expected);

        User actual = userDao.findByLogin("login");
        assertEquals(expected, actual);
    }

    @Test
    public void findById() {
        User expected = new User("username", "login", "password");
        userDao.save(expected);
        List<User> users = userDao.getAllUsers();
        long id = users.get(0).getId();

        User actual = userDao.findById(id);
        assertEquals(expected, actual);
    }

    @Test
    public void getAllUsers() {
        userDao.save(new User("username1", "login1", "password1"));
        userDao.save(new User("username2", "login2", "password2"));
        userDao.save(new User("username3", "login3", "password3"));

        List<User> users = userDao.getAllUsers();
        assertEquals(3, users.size());
    }
}