package com.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        System.out.println("Hello");
        try {
            tx.begin();
            logic(em);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    private static void logic(EntityManager em) {
        User user = new User();
        user.setId(1L);
        user.setName("강경영");
        user.setAge(42);

        em.persist(user);

        user.setAge(20);

        User findUser = em.find(User.class, 1L);
        System.out.println(findUser);

        em.remove(findUser);

        findUser = em.find(User.class, 1L);
        System.out.println(findUser);

    }
}
