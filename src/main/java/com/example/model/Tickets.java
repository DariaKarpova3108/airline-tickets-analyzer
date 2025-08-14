package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tickets {
   private List<Ticket> tickets;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Ticket {
        private String origin;
        private String originName;
        private String destination;
        private String destinationName;
        private LocalDate departureDate;
        private LocalTime departureTime;
        private LocalDate arrivalDate;
        private LocalTime arrivalTime;
        private String carrier;
        private String stops;
        private BigDecimal price;
    }
}
