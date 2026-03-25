package demo.restaurant.dto.existing;

import demo.restaurant.enums.Weekday;

import java.time.LocalTime;

public record OpeningHours (
        Long id,
        Weekday weekday,
        LocalTime openingTime,
        LocalTime closingTime
) {}
