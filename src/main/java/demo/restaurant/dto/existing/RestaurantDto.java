package demo.restaurant.dto.existing;

import demo.restaurant.enums.Category;
import demo.restaurant.model.Address;

public record RestaurantDto(
        Long id,
        String name,
        Address address,
        Category category,
        double rating,
        double meanPrice,
        String imagePath
) {}
