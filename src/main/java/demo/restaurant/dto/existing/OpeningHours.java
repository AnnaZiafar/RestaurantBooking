package demo.restaurant.dto.existing;

import demo.restaurant.enums.Weekday;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpeningHours {
    Long id;
    Weekday weekday;
    LocalTime openingTime;
    LocalTime closingTime;
}
