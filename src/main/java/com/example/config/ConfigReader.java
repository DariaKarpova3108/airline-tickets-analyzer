package com.example.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private final Properties properties = new Properties();

    public ConfigReader() throws IOException {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (inputStream == null) {
                throw new FileNotFoundException("Файл config.properties не найден в classpath");
            }

            properties.load(inputStream);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
