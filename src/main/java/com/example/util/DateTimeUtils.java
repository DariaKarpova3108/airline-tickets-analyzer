package com.example.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateTimeUtils {

    public static LocalDateTime toZonedDateTime(LocalDate date, LocalTime time) {
        return LocalDateTime.of(date, time);
    }
}
