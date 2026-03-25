package demo.restaurant.dto.create;

import demo.restaurant.model.DiningTable;
import demo.restaurant.model.Restaurant;

import java.time.LocalDate;
import java.time.LocalTime;

public record CreateBookingDto (
        String customerName,
        Restaurant restaurant,
        DiningTable table,
        LocalDate date,
        LocalTime bookingStart,
        LocalTime bookingEnd
){}
