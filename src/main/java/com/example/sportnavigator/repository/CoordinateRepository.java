package com.example.sportnavigator.repository;

import com.example.sportnavigator.Models.Coordinate;
import com.example.sportnavigator.Models.SportCourt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordinateRepository extends JpaRepository<Coordinate, Long> {
    public Coordinate findBySportCourt(SportCourt court);
}
