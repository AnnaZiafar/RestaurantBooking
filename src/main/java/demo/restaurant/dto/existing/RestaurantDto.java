package demo.restaurant.dto.existing;

import demo.restaurant.enums.Category;
import demo.restaurant.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDto {
    Long id;
    String name;
    Address address;
    Category category;
    double rating;
    double meanPrice;
    String imagePath;
}
