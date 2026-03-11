package demo.restaurant.repository;

import demo.restaurant.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Set<Restaurant> findByCategory(String category);

    Set<Restaurant> findByNameContainingIgnoreCase(String name);

    Set<Restaurant> findByRatingGreaterThanEqual(double rating);

    boolean existsByName(String name);

    @Query("select distinct r.address.city from Restaurant r order by r.address.city asc")
    Set<String> findDistinctCities();
}
