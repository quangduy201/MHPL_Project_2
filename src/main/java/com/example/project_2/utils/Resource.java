package com.example.project_2.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Resource {
    public static Path getPathFromResource(String relativePath) {
        try {
            URL url = Resource.class.getResource("/" + relativePath);
            if (url != null)
                return Paths.get(url.toURI());
            System.out.println("Can't get the URL from the root: " + relativePath);
        } catch (URISyntaxException e) {
            System.out.println(e);
        }
        return null;
    }

    public static InputStream getInputStreamFromRoot(String relativePath) {
        return Resource.class.getResourceAsStream("/" + relativePath);
    }

    public static Properties loadProperties(String relativePath) throws IOException {
        try (InputStream inputStream = getInputStreamFromRoot(relativePath)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        }
    }

    public static void saveProperties(Properties properties, File file) throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream(file.getAbsolutePath())) {
            properties.store(outputStream, null);
        }
    }
}
