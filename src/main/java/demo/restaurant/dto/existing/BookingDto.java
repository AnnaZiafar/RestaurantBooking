package demo.restaurant.dto.existing;

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
public class BookingDto {
    Long id;
    String customerName;
    Restaurant restaurant;
    DiningTable table;
    LocalDate date;
    LocalTime bookingStart;
    LocalTime bookingEnd;
}
