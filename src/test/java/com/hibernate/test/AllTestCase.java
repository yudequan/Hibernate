package com.hibernate.test;

import com.jpa.domain.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

/**
 * Created by yudequan on 08/03/2017.
 */
public class AllTestCase
{
    @Test
    public void first()
    {
        StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
        serviceRegistryBuilder
                .applySetting("hibernate.connection.url", "jdbc:oracle:thin:@106.14.16.212:1521:orcl")
                .applySetting("hibernate.connection.driver_class", "oracle.jdbc.driver.OracleDriver")
                .applySetting("hibernate.connection.username", "scott")
                .applySetting("hibernate.connection.password", "tiger")
                .applySetting("hibernate.show_sql", "true")
                .applySetting("hibernate.format_sql", "true")
                .applySetting("hibernate.hbm2ddl.auto", "create")
                .applySetting("hibernate.use_sql_comments", "true")
                .applySetting("hibernate.current_session_context_class", "thread");

        ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        metadataSources.addAnnotatedClass(Message.class);
        // metadataSources.addPackage(Message.class.getPackage());
        MetadataBuilder metadataBuilder = metadataSources.getMetadataBuilder();
        Metadata metadata = metadataBuilder.build();

        SessionFactory sessionFactory = metadata.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        Transaction transaction = session.getTransaction();
        transaction.begin();

        Message message = new Message();
        message.setText("Hello World....");

        session.persist(message);

        transaction.commit();

        session.close();
    }
}
