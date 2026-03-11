package demo.restaurant.model;

import jakarta.persistence.*;
import lombok.*;

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

    private String firstName;
    private String lastName;
    private String email;

    private LocalDate date;
    private LocalTime bookingStart;
    private LocalTime bookingEnd;

    public Booking(Customer customer, Restaurant restaurant, DiningTable table, LocalDate date, LocalTime bookingStart, LocalTime bookingEnd) {
        this.customer = customer;
        this.restaurant = restaurant;
        this.table = table;
        this.date = date;
        this.bookingStart = bookingStart;
        this.bookingEnd = bookingEnd;

        this.firstName = customer.getFirstname();
        this.lastName = customer.getLastname();
        this.email = customer.getEmail();
    }

    public Booking(Restaurant restaurant, DiningTable table, String firstName, String lastName, String email, LocalDate date, LocalTime bookingStart, LocalTime bookingEnd) {
        this.restaurant = restaurant;
        this.table = table;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.date = date;
        this.bookingStart = bookingStart;
        this.bookingEnd = bookingEnd;
    }
}