package com.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ObjectMapperSingleton {
    private static final ObjectMapper INSTANCE = createInstanceObjectMapper();

    private static final String DATE_FORMAT="dd.MM.yy";
    private static final String TIME_FORMAT="H:mm";

    private static ObjectMapper createInstanceObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(TIME_FORMAT);

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(timeFormatter));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(timeFormatter));

        objectMapper.registerModule(javaTimeModule);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }

    public static ObjectMapper getInstance() {
        return INSTANCE;
    }
}
