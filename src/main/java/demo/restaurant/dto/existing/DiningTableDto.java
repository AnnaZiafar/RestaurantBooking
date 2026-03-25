package demo.restaurant.dto.existing;

import demo.restaurant.model.Restaurant;

public record DiningTableDto(
        Long id,
        Restaurant restaurant,
        int tableNumber,
        int seatCapacity
) {}
