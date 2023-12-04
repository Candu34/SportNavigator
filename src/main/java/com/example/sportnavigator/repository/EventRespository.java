package com.example.sportnavigator.repository;

import com.example.sportnavigator.Models.Event;
import com.example.sportnavigator.Models.SportCourt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRespository extends JpaRepository<Event, Long> {

    public List<Event> findBySportCourt(SportCourt sportCourt);
}
