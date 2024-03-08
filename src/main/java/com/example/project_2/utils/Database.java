package com.example.project_2.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Database {
    private static final String DATABASE_FILE = "settings/db.properties";
    private static final Scanner scanner = new Scanner(System.in);

    public static void initializeDatabase() throws IOException {
        System.out.print("Enter database's name: ");
        String dbSchema = scanner.nextLine().trim();
        System.out.print("Enter database's username: ");
        String dbUsername = scanner.nextLine().trim();
        System.out.print("Enter database's password: ");
        String dbPassword = scanner.nextLine().trim();

        Properties properties = new Properties();
        properties.put("db.schema", dbSchema);
        properties.put("db.username", dbUsername);
        properties.put("db.password", dbPassword);
        ClassLoader classLoader = Database.class.getClassLoader();
        URL url = classLoader.getResource(DATABASE_FILE);
        File file = new File(Objects.requireNonNull(url).getFile());
        Resource.saveProperties(properties, file);
    }

    public static List<String> getDatabaseConfiguration() throws IOException {
        List<String> result = new ArrayList<>();
        Properties properties = Resource.loadProperties(DATABASE_FILE);
        String dbDatabase = properties.getProperty("db.schema");
        String dbUsername = properties.getProperty("db.username");
        String dbPassword = properties.getProperty("db.password");
        String dbUrl = String.format("jdbc:mysql://localhost:3306/%s?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8", dbDatabase);
        result.add(dbUrl);
        result.add(dbUsername);
        result.add(dbPassword);
        return result;
    }
}
