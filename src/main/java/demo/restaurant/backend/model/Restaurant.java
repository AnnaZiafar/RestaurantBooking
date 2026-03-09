package demo.restaurant.backend.model;

import demo.restaurant.backend.enums.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString(exclude = {"openingHours", "tables"})
@NoArgsConstructor
@Entity
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Embedded
    private Address address;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "restaurant_opening_hours",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "opening_hours_id")
    )
    private Set<OpeningHours> openingHours = new HashSet<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DiningTable> tables = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private Category category;

    private double rating;
    private double meanPrice;
    private String imagePath;

    public Restaurant(String name, Address address, Category category, double rating, double meanPrice, String imagePath){
        this.name = name;
        this.address = address;
        this.category = category;
        this.rating = rating;
        this.meanPrice = meanPrice;
        this.imagePath = imagePath;
    }

}