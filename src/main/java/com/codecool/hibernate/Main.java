package com.codecool.hibernate;

import com.codecool.hibernate.entity.Employee;
import com.codecool.hibernate.persistenceUtil.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;

/*
* https://www.journaldev.com/3481/hibernate-session-merge-vs-update-save-saveorupdate-persist-example
*/
public class Main {


    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        saveMethodExample(sessionFactory);
        persistMethodExample(sessionFactory);
        saveOrUpdateMethodExample(sessionFactory);

        sessionFactory.close();
    }

    private static void saveOrUpdateMethodExample(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Employee laci2 = session.load(Employee.class, 3L);
        laci2.setFirstName("Szőlő");
        laci2.setLastName("Lacika");

        session.saveOrUpdate(laci2);

        session.getTransaction().commit();

    }

    // work within the boundary of transaction
    // return with nothing
    private static void persistMethodExample(SessionFactory sessionFactory) {
        Employee laci = new Employee("Szőllős", "László", 2500);

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.persist(laci);

        session.getTransaction().commit();

        System.out.println(session.load(Employee.class, laci.getId()).getFirstName());
    }

    private static void saveMethodExample(SessionFactory sessionFactory) {
        Employee pista = new Employee("Kelemen", "Pisti", 1500);
        Employee gezu = new Employee("Horváth", "Géza", 1200);

        Session sessionOne = sessionFactory.openSession();
        sessionOne.beginTransaction();

        sessionOne.saveOrUpdate(pista);
//        long pistaId = (Long) sessionOne.save(pista);
        sessionOne.save(gezu);

        sessionOne.getTransaction().commit();
    }

    private static void populateDb(EntityManager em) {
        Employee pista = new Employee("Kelemen", "Pisti", 1500);
        Employee gezu = new Employee("Horváth", "Géza", 1200);

        em.getTransaction().begin();
        em.persist(pista);
        em.persist(gezu);
        em.getTransaction().commit();
        System.out.println("Persisted " + gezu.getId());
    }

}
