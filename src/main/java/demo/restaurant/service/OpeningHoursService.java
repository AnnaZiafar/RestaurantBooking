package demo.restaurant.service;

import demo.restaurant.model.OpeningHours;
import demo.restaurant.model.Restaurant;
import demo.restaurant.repository.OpeningHoursRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class OpeningHoursService {

    private final OpeningHoursRepository openingHoursRepository;

    @Transactional
    public void syncOpeningHours(Restaurant restaurant){
        Set<OpeningHours> openingHours = restaurant.getOpeningHours();
        Set<OpeningHours> managedHours = new HashSet<>();

        for(OpeningHours timeslot : openingHours){
            managedHours.add(findOrCreateTimeslot(timeslot));
        }

        restaurant.setOpeningHours(managedHours);
    }

    private OpeningHours findOrCreateTimeslot(OpeningHours openingHours){
        return openingHoursRepository.findByWeekdayAndOpeningTimeAndClosingTime(
                openingHours.getWeekday(),
                openingHours.getOpeningTime(),
                openingHours.getClosingTime()
        )
                .orElseGet(() -> openingHoursRepository.save(openingHours));
    }
}
