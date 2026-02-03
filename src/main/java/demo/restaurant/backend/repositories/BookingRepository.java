package demo.restaurant.backend.repositories;

import demo.restaurant.backend.model.Booking;
import demo.restaurant.backend.model.Customer;
import demo.restaurant.backend.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    Set<Booking> findByRestaurantAndDate(Restaurant restaurant, LocalDate date);

    Optional<Booking> findByRestaurantAndCustomer(
            Restaurant restaurant, Customer customer);
}
