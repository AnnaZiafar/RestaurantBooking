package demo.restaurant.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "dining_table")
public class DiningTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    private int tableNumber;
    private int seatCapacity;

    public DiningTable(Restaurant restaurant, int tableNumber, int seatCapacity) {
        this.restaurant = restaurant;
        this.tableNumber = tableNumber;
        this.seatCapacity = seatCapacity;
    }
}