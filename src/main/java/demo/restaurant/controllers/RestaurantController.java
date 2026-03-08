package demo.restaurant.controllers;

import demo.restaurant.backend.services.RestaurantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Set;

@Controller
public class RestaurantController {

    RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService){
        this.restaurantService = restaurantService;
    }

    @ModelAttribute("cities")
    public Set<String> getCities(){
        return restaurantService.getCities();
    }

    @GetMapping("/get-header")
    public String getHeader(){
        return "header";
    }

    @GetMapping("/")
    public String loadRestaurants(Model model){
        model.addAttribute("restaurants", restaurantService.getAll());
        return "main";
    }

}
