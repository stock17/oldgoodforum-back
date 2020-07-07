package ru.yurima.oldgoodforumback.entities;

import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.Assert.*;

public class UserTest {

    EntityManager em;

    @Before
    public void init(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ru.yurima.oldgoodforumback.entities");
        em = emf.createEntityManager();
    }

    @Test
    public void testUserPersistence(){

        EntityTransaction tx =  em.getTransaction();
        tx.begin();
        String[] userData1 = new String[]{"John Snow", "John133", "123456"};
        String[] userData2 = new String[]{"John Week", "John666", "0000"};
        User user1 = new User(userData1[0],userData1[1], userData1[2]);
        User user2 = new User(userData2[0],userData2[1], userData2[2]);
        em.persist(user1);
        em.persist(user2);
        tx.commit();

        tx =  em.getTransaction();
        tx.begin();
        List<User> userList = em.createQuery("SELECT u FROM User u", User.class).getResultList();
        tx.commit();
        for (User u : userList) {
            System.out.println(u);
        }
        Assert.assertEquals(userData1[0], userList.get(0).getName());
        Assert.assertEquals(userData1[1], userList.get(0).getLogin());
        Assert.assertEquals(userData1[2], userList.get(0).getPassword());
    }

    @After
    public void destroy(){
        em.close();
    }

}