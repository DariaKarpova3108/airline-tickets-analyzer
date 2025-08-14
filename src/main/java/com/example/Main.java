package com.example;

import com.example.config.ConfigReader;
import com.example.service.FlightStatisticsService;
import com.example.service.Reader;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        try {
            FlightStatisticsService service = new FlightStatisticsService(new Reader(new ConfigReader()));
            Map<String, Duration> minFlightDurationByCarrier = service.getMinFlightDurationByCarrier();

            System.out.println("Минимальное время полета между городами Владивосток и Тель-Авив:");
            minFlightDurationByCarrier.forEach((key, value) -> {
                long totalMinutes = value.toMinutes();
                long hours = totalMinutes / 60;
                long minutes = totalMinutes % 60;
                System.out.format(" - у перевозчика - %s" +
                        " минимальное время полета: %d ч %d мин\n", key, hours, minutes);
            });

            System.out.println("\nРазницу между средней ценой  и медианой для полета между " +
                    "городами Владивосток и Тель-Авив = " + service.calculatePriceDifferenceAverageAndMedian());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}