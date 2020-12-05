package ru.yurima.oldgoodforumback.db;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

    private static EntityManagerFactory factory;
    private static final String unitName = "forum";

    private HibernateUtil(){}

    public static EntityManagerFactory getFactory() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory(unitName);
        }
        return factory;
    }
}
