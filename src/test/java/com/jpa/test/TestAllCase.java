package com.jpa.test;

import com.jpa.domain.Message;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by yudequan on 08/03/2017.
 */
public class TestAllCase
{
    @Test
    public void first()
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CRM");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Message msg = new Message();
        msg.setText("Hello World");

        em.persist(msg);

        tx.commit();

        em.close();
    }
}
