package com.example.service;

import com.example.config.ConfigReader;
import com.example.config.ObjectMapperSingleton;
import com.example.model.Tickets;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@RequiredArgsConstructor
public class Reader {
    private final ConfigReader configReader;

    public Tickets readFile() throws IOException {
        ObjectMapper objectMapper = ObjectMapperSingleton.getInstance();
        String fileName = configReader.getProperty("file-name");

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName)) {

            if (inputStream == null) {
                throw new FileNotFoundException("Файл " + fileName + " не найден");
            }

            return objectMapper.readValue(inputStream, Tickets.class);
        } catch (IOException ex) {
            throw ex;
        }
    }
}
