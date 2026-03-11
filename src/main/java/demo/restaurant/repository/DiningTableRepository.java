package demo.restaurant.repository;

import demo.restaurant.model.DiningTable;
import demo.restaurant.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public interface DiningTableRepository extends JpaRepository<DiningTable, Long> {

    Set<DiningTable> findByRestaurant(Restaurant restaurant);

    Set<DiningTable> findByRestaurantAndSeatCapacityGreaterThanEqual(
            Restaurant restaurant, int seatCapacity
    );

    Set<DiningTable> findByRestaurantAndSeatCapacity(
            Restaurant restaurant, int seatCapacity
    );

    Set<DiningTable> findByRestaurantAndSeatCapacityBetween(
            Restaurant restaurant, int minCapacity, int maxCapacity
    );

    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END " +
            "FROM Booking b WHERE b.restaurant = :restaurant " +
            "AND b.table = :table " +
            "AND b.date = :date " +
            "AND b.bookingStart < :endTime " +
            "AND b.bookingEnd > :startTime")
    boolean isTableReserved(
            @Param("restaurant") Restaurant restaurant,
            @Param("table") DiningTable table,
            @Param("date") LocalDate date,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime
    );

    /**
     * Find the largest seat capacity at a restaurant
     * @param restaurant name of the restaurant
     * @return largest table
     */
    DiningTable findFirstByRestaurantOrderBySeatCapacityDesc(
            Restaurant restaurant
    );
}
