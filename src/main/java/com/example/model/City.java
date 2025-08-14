package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum City {
    VLADIVOSTOK("Владивосток"),
    TEL_AVIV("Тель-Авив");
    private final String city;
}
