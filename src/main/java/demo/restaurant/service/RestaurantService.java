package demo.restaurant.service;

import demo.restaurant.model.Restaurant;
import demo.restaurant.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    private RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> getAll(){
        return restaurantRepository.findAll();
    }

    public Set<String> getCities() { return restaurantRepository.findDistinctCities();}
}
