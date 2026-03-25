package demo.restaurant.dto.create;

import demo.restaurant.model.DiningTable;
import demo.restaurant.model.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookingDto{
    String customerName;
    Restaurant restaurant;
    DiningTable table;
    LocalDate date;
    LocalTime bookingStart;
    LocalTime bookingEnd;
}
