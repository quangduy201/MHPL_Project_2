package com.example.project_2.utils;

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
            Database.initializeDatabase();
            buildSessionFactory();
        } catch (Exception e) {
            System.out.println("Failed to connect.");
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
