package demo.restaurant.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "restaurant_opening_hours",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "opening_hours_id")
    )
    @ToString.Exclude
    private List<OpeningHours> openingHours = new ArrayList<>();

    private String category;
    private String address;
    private double rating;
    private double meanPrice;
    private String imagePath;

    public Restaurant(String name, String category, String address, double rating, double meanPrice, String imagePath){
        this.name = name;
        this.category = category;
        this.address = address;
        this.rating = rating;
        this.meanPrice = meanPrice;
        this.imagePath = imagePath;
    }
}