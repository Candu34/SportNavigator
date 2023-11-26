package com.example.sportnavigator.repository;

import com.example.sportnavigator.Models.Review;
import com.example.sportnavigator.Models.SportCourt;
import com.example.sportnavigator.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    public List<Review> findBySportCourt(SportCourt sportCourt);
    public List<Review> findByUser(User user);
    public List<Review> findByRating(int rating);
}
