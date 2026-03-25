package demo.restaurant.service;

import demo.restaurant.dto.existing.RestaurantDto;
import demo.restaurant.model.Restaurant;
import demo.restaurant.repository.RestaurantRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final ModelMapper modelMapper;

    private RestaurantService(RestaurantRepository restaurantRepository, ModelMapper modelMapper) {
        this.restaurantRepository = restaurantRepository;
        this.modelMapper = modelMapper;
    }

    public List<RestaurantDto> getAll(){
        return restaurantRepository.findAll().stream()
                .map(restaurant -> modelMapper.map(restaurant, RestaurantDto.class))
                .toList();
    }

    public Set<String> getCities() {
        return restaurantRepository.findDistinctCities();
    }
}
