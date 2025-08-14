package com.example.service;

import com.example.model.City;
import com.example.model.Tickets;
import com.example.util.DateTimeUtils;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class FlightStatisticsService {
    private final Reader reader;

    public Map<String, Duration> getMinFlightDurationByCarrier() {
        Tickets tickets = getTickets();
        return calculateMinDurationByCarrier(tickets);
    }

    private Duration calculateDuration(Tickets.Ticket ticket) {
        LocalDateTime departureDateTime = DateTimeUtils.toZonedDateTime(
                ticket.getDepartureDate(), ticket.getDepartureTime());
        LocalDateTime arrivalDateTime = DateTimeUtils.toZonedDateTime(
                ticket.getArrivalDate(), ticket.getArrivalTime());
        return Duration.between(departureDateTime, arrivalDateTime);
    }

    private Map<String, Duration> calculateMinDurationByCarrier(Tickets tickets) {
        Map<String, Duration> mapCarrierAndMinDuration = new HashMap<>();

        tickets.getTickets().stream()
                .filter(t -> t.getOriginName().equals(City.VLADIVOSTOK.getCity()) &&
                        t.getDestinationName().equals(City.TEL_AVIV.getCity()))
                .collect(Collectors.groupingBy(Tickets.Ticket::getCarrier))
                .forEach((carrier, carrierTickets) -> {
                    Duration minDuration = carrierTickets.stream()
                            .map(this::calculateDuration)
                            .min(Duration::compareTo)
                            .orElse(Duration.ZERO);
                    mapCarrierAndMinDuration.put(carrier, minDuration);
                });

        return mapCarrierAndMinDuration;
    }

    public double calculatePriceDifferenceAverageAndMedian() {
        List<Double> priceList = getFilteredPriceList();

        double averagePrice = priceList.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0);


        double medianPrice;
        int size = priceList.size();

        if (size == 0) {
            medianPrice = 0;
        } else if (size % 2 == 1) {
            medianPrice = priceList.get(size / 2);
        } else {
            medianPrice = (priceList.get(size / 2 - 1) + priceList.get(size / 2)) / 2;
        }

        return averagePrice - medianPrice;
    }

    private Tickets getTickets() {
        try {
            return reader.readFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Double> getFilteredPriceList() {
        Tickets tickets = getTickets();
        return tickets.getTickets().stream()
                .filter(t -> t.getOriginName().equals(City.VLADIVOSTOK.getCity()) &&
                        t.getDestinationName().equals(City.TEL_AVIV.getCity()))
                .map(t -> t.getPrice().doubleValue())
                .sorted()
                .toList();
    }
}
