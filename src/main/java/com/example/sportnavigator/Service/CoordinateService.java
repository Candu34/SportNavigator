package com.example.sportnavigator.Service;

import com.example.sportnavigator.Models.Coordinate;
import com.example.sportnavigator.Models.SportCourt;
import com.example.sportnavigator.repository.CoordinateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CoordinateService {
    private final CoordinateRepository coordinateRepository;

    @Transactional
    public void save(Coordinate coordinate) {
        coordinateRepository.save(coordinate);
    }

    public Coordinate findBySportCourt(SportCourt sportCourt) {
        return coordinateRepository.findBySportCourt(sportCourt);
    }

}
