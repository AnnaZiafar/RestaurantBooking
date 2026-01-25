package demo.restaurant.backend.repositories;

import demo.restaurant.backend.entities.Booking;
import demo.restaurant.backend.entities.Customer;
import demo.restaurant.backend.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    Set<Booking> findByRestaurantAndDate(Restaurant restaurant, LocalDate date);

    Optional<Booking> findByRestaurantAndCustomer(
            Restaurant restaurant, Customer customer);
}
