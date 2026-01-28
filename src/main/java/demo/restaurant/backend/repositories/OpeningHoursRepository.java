package demo.restaurant.backend.repositories;

import demo.restaurant.backend.entities.OpeningHours;
import demo.restaurant.backend.entities.Restaurant;
import demo.restaurant.backend.enums.Weekday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.Optional;
import java.util.Set;

public interface OpeningHoursRepository extends JpaRepository<OpeningHours, Long> {

    Optional<OpeningHours> findByWeekdayAndOpeningTimeAndClosingTime(
            Weekday weekday, LocalTime openingTime, LocalTime closingTime);


    @Query("SELECT r FROM OpeningHours r " +
    "WHERE :restaurant MEMBER OF r.restaurants " +
    "AND r.weekday = :weekday")
    Optional<OpeningHours> findOpeningHoursByRestaurantAndWeekday(
            @Param("restaurant") Restaurant restaurant,
            @Param("weekday") Weekday weekday
    ) ;

}
