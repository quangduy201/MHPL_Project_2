package com.example.project_2.utils;

import com.example.project_2.Project_2;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.List;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null)
            initialize();
        return sessionFactory;
    }

    public static void initialize() {
        try {
            if (!Database.testConnection()) {
                Database.initializeDatabase();
            }
            buildSessionFactory();
        } catch (HibernateException e) {
            System.out.println("Failed to connect: " + e.getMessage());
            Project_2.shutdown();
        } catch (Exception e) {
            System.out.println("Failed to connect: " + e.getMessage());
            initialize();
        }
    }

    public static void shutdown() {
        if (sessionFactory != null)
            sessionFactory.close();
    }

    public static void buildSessionFactory() throws IOException {
        List<String> properties = Database.getDatabaseConfiguration();
        Configuration configuration = new Configuration().configure("settings/hibernate.cfg.xml");
        configuration.setProperty("hibernate.connection.url", properties.get(0));
        configuration.setProperty("hibernate.connection.username", properties.get(1));
        configuration.setProperty("hibernate.connection.password", properties.get(2));

        sessionFactory = configuration.buildSessionFactory();
    }
}
