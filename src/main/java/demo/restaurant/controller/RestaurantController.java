package demo.restaurant.controller;

import demo.restaurant.enums.Category;
import demo.restaurant.service.RestaurantService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/init-data")
    public Map<String, Object> getInitialData() {
        Map<String, Object> data = new HashMap<>();
        data.put("restaurants", restaurantService.getAll());
        data.put("categories", Category.values());
        data.put("cities", restaurantService.getCities());
        return data;
    }
}