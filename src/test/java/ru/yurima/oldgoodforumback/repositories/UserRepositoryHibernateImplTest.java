package ru.yurima.oldgoodforumback.repositories;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yurima.oldgoodforumback.entities.User;
import java.util.Optional;

import static org.junit.Assert.*;

public class UserRepositoryHibernateImplTest {
    
    private static final String name = "John Smith";
    private static final String login = "johnny123";
    private static final String password = "12345678";

    private final UserRepository userRepository = new UserRepositoryHibernateImpl();

    @Before
    public void init(){

    }

    @After
    public void destroy(){
        userRepository.deleteAll();
    }

    @Test
    public void testSave() {
        User user = new User(name, login, password);
        userRepository.save(user);
        assertTrue(userRepository.findAll().size() > 0);
        assertEquals(user, userRepository.findAll().get(0));
    }

    @Test
    public void testFindById() {
        User user = new User(name, login, password);
        userRepository.save(user);
        long id = user.getId();
        Optional<User> optional = userRepository.findById(id);
        assertTrue(optional.isPresent());
        assertEquals(user, optional.get());
    }

    @Test
    public void testFindByLogin() {
        User user = new User(name, login, password);
        userRepository.save(user);
        Optional<User> optional = userRepository.findByLogin(login);
        assertTrue(optional.isPresent());
        assertEquals(user, optional.get());
        Optional<User> optional1 = userRepository.findByLogin("NO_SUCH_LOGIN");
        assertFalse(optional1.isPresent());
    }

    @Test
    public void testFindAll() {
        User user = new User(name, login, password);
        userRepository.save(user);
        assertEquals(1, userRepository.findAll().size());
        User user2 = new User(name, login, password);
        userRepository.save(user2);
        assertEquals(2, userRepository.findAll().size());
    }

    @Test
    public void testDelete() {
        User user = new User(name, login, password);
        userRepository.save(user);
        assertEquals(1, userRepository.findAll().size());
        userRepository.delete(user);
        assertEquals(0, userRepository.findAll().size());
    }

    @Test
    public void testDeleteById() {
        User user = new User(name, login, password);
        userRepository.save(user);
        assertEquals(1, userRepository.findAll().size());
        long id = user.getId();
        userRepository.deleteById(id);
        assertEquals(0, userRepository.findAll().size());
    }

    @Test
    public void testDeleteAll() {
        User user = new User(name, login, password);
        User user2 = new User(name, login, password);
        userRepository.save(user);
        userRepository.save(user2);
        assertEquals(2, userRepository.findAll().size());
        userRepository.deleteAll();
        assertEquals(0, userRepository.findAll().size());
    }
}