package demo.restaurant.backend.services;

import demo.restaurant.backend.entities.OpeningHours;
import demo.restaurant.backend.entities.Restaurant;
import demo.restaurant.backend.repositories.OpeningHoursRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class OpeningHoursService {

    private final OpeningHoursRepository openingHoursRepository;

    public void reuseOrCreateOpeningHours(Restaurant restaurant){
        Set<OpeningHours> openingHours = restaurant.getOpeningHours();
        Set<OpeningHours> managedHours = new HashSet<>();

        for(OpeningHours timeslot : openingHours){
            Optional<OpeningHours> exist = openingHoursRepository
                    .findByWeekdayAndOpeningTimeAndClosingTime(
                            timeslot.getWeekday(),
                            timeslot.getOpeningTime(),
                            timeslot.getClosingTime()
                    );

            if(exist.isPresent())
                managedHours.add(exist.get());
            else
                managedHours.add(openingHoursRepository.save(timeslot));
        }

        restaurant.setOpeningHours(managedHours);
    }
}
