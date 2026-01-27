package demo.restaurant.backend.repositories;

import demo.restaurant.backend.entities.OpeningHours;
import demo.restaurant.backend.enums.Weekday;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.Optional;

public interface OpeningHoursRepository extends JpaRepository<OpeningHours, Long> {

    Optional<OpeningHours> findByWeekdayAndOpeningTimeAndClosingTime(
            Weekday weekday, LocalTime openingTime, LocalTime closingTime);

    boolean existsByWeekdayAndOpeningTimeAndClosingTime(Weekday weekday, LocalTime openingTime, LocalTime closingTime);
}
