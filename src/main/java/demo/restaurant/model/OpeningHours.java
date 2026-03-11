package demo.restaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import demo.restaurant.enums.Weekday;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "opening_hours", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"weekday", "openingTime", "closingTime"})
})
public class OpeningHours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Weekday weekday;

    private LocalTime openingTime;
    private LocalTime closingTime;

    @ManyToMany(mappedBy = "openingHours")
    @JsonIgnore
    private Set<Restaurant> restaurants = new HashSet<>();

    public OpeningHours(Weekday weekday, LocalTime openingTime, LocalTime closingTime){
        this.weekday = weekday;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

}

