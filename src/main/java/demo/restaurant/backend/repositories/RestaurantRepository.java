package demo.restaurant.backend.repositories;

import demo.restaurant.backend.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Set<Restaurant> findByCategory(String category);

    Set<Restaurant> findByNameContainingIgnoreCase(String name);

    Set<Restaurant> findByRatingGreaterThanEqual(double rating);

    boolean existsByName(String name);
}
