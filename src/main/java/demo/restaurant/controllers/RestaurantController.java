package demo.restaurant.controllers;

import demo.restaurant.backend.services.RestaurantService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RestaurantController {

    RestaurantService restaurantService;

    private RestaurantController(RestaurantService restaurantService){
        this.restaurantService = restaurantService;
    }

    @GetMapping("/main")
    public String loadRestaurants(Model model){
        model.addAttribute("restaurants", restaurantService.getAll());
        return "main";
    }

}
