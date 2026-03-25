package demo.restaurant.dto.existing;

import demo.restaurant.model.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiningTableDto {
    Long id;
    Restaurant restaurant;
    int tableNumber;
    int seatCapacity;
}
