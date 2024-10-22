package org.jpmc.OBSUtil;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

import jakarta.persistence.EntityManager; // Change to jakarta.persistence

import org.jpmc.OBS.entity.*;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/Haridb");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "root");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
                settings.put(Environment.SHOW_SQL, "false");
                settings.put(Environment.HBM2DDL_AUTO, "update"); // Use "update" for existing databases
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                configuration.setProperties(settings);

                // Add annotated classes
                configuration.addAnnotatedClass(Admin.class);
           configuration.addAnnotatedClass(Account.class);
              configuration.addAnnotatedClass(Customer.class);
              configuration.addAnnotatedClass(Branch.class);
              configuration.addAnnotatedClass(Loan.class);
                configuration.addAnnotatedClass(Payment.class);
                configuration.addAnnotatedClass(Transaction.class);
                configuration.addAnnotatedClass(User.class);

                // Build the ServiceRegistry and SessionFactory
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                System.out.println("Hibernate Java Config serviceRegistry created");

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                System.err.println("Initial SessionFactory creation failed." + e);
                throw new ExceptionInInitializerError(e); // Rethrow to fail startup
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close(); // Closing SessionFactory                    
        }
    }

    public static EntityManager getEntityManager() {
        // Implement this method if you need to return an EntityManager
        return null;
    }
}
