package com.example.sportnavigator.Service;

import com.example.sportnavigator.Models.Event;
import com.example.sportnavigator.Models.SportCourt;
import com.example.sportnavigator.repository.EventRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EventService {

    private final EventRespository eventRespository;


    @Transactional
    public void save(Event event) {
        eventRespository.save(event);
    }

    public Event findById(Long id) {
        return eventRespository.findById(id).orElse(null); // TODO Exception handler
    }

    @Transactional
    public void delete(Event event) {
        eventRespository.delete(event);
    }

    public List<Event> findBySportCourt(SportCourt sportCourt) {

        return eventRespository.findBySportCourt(sportCourt);
    }

    public List<Event> findAll(){
        return eventRespository.findAll();
    }

}
