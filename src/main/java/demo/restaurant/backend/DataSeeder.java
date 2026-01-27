package demo.restaurant.backend;

import demo.restaurant.backend.entities.Restaurant;
import demo.restaurant.backend.repositories.RestaurantRepository;
import demo.restaurant.backend.services.OpeningHoursService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final RestaurantRepository restaurantRepository;
    private final OpeningHoursService openingHoursService;
    private final ObjectMapper objectMapper;

    @Transactional
    @Override
    public void run(String... args) throws Exception {

        InputStream inputStream = new ClassPathResource("data.json").getInputStream();
        Set<Restaurant> restaurants = objectMapper.readValue(inputStream, new TypeReference<>() {});

        for(Restaurant restaurant : restaurants){
            if(!restaurantRepository.existsByName(restaurant.getName())){
                restaurant.getTables().forEach(table -> table.setRestaurant(restaurant));
                openingHoursService.reuseOrCreateOpeningHours(restaurant);
                restaurantRepository.save(restaurant);
            }
        }


    }


}
