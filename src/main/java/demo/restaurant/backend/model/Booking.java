package demo.restaurant.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private DiningTable table;

    private LocalDate date;
    private LocalTime bookingStart;
    private LocalTime bookingEnd;

    public Booking(Customer customer, Restaurant restaurant, DiningTable table,
                   LocalDate date, LocalTime bookingStart, LocalTime bookingEnd){

        this.customer = customer;
        this.restaurant = restaurant;
        this.table = table;
        this.date = date;
        this.bookingStart = bookingStart;
        this.bookingEnd = bookingEnd;

    }

}