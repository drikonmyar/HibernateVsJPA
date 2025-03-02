package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Snake s1 = new Snake();
        s1.setSname("Jaldhora");
        s1.setFood("Fish");

        // Hibernate
//        callHibernate(s1);

        // JPA
//        callJPA(s1);
        jpaUsingNativeQuery();

    }

    private static void jpaUsingNativeQuery(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        List<Snake> listOfSnakes = em.createNativeQuery("select * from snake_data where food = :food", Snake.class).setParameter("food", "Human").getResultList();
        System.out.println(listOfSnakes);
        em.close();
    }

    private static void callJPA(Snake s1) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(s1);
        em.getTransaction().commit();
        em.close();
    }

    private static void callHibernate(Snake s1){
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Snake.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(s1);
        transaction.commit();
        session.close();
    }
}