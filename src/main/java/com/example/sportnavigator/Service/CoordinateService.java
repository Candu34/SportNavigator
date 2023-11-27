package com.example.sportnavigator.Service;

import com.example.sportnavigator.Models.Coordinate;
import com.example.sportnavigator.Models.SportCourt;
import com.example.sportnavigator.repository.CoordinateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoordinateService {
    private final CoordinateRepository coordinateRepository;

    public void save(Coordinate coordinate) {
        coordinateRepository.save(coordinate);
    }

    public Coordinate findBySportCourt(SportCourt sportCourt) {
        return coordinateRepository.findBySportCourt(sportCourt);
    }

}
