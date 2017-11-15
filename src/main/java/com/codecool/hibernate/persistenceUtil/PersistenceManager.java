package com.codecool.hibernate.persistenceUtil;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceManager {

    private static EntityManagerFactory instance;

    private PersistenceManager() { }

    public static EntityManagerFactory getEntityManagerFactory() {
        if (instance == null) {
            instance = Persistence
                    .createEntityManagerFactory("hibernatePractice");
        }
        return instance;
    }

}
