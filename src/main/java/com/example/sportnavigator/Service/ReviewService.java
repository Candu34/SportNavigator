package com.example.sportnavigator.Service;


import com.example.sportnavigator.Models.Review;
import com.example.sportnavigator.Models.SportCourt;
import com.example.sportnavigator.Models.User;
import com.example.sportnavigator.repository.ReviewRepository;
import com.example.sportnavigator.util.exceptions.ReviewNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Transactional()
    public void save(Review review) {
        reviewRepository.save(review);
    }

    public List<Review> findBySportCourt(SportCourt sportCourt) {
        return reviewRepository.findBySportCourt(sportCourt);
    }

    public List<Review> findByUser(User user) {
        return reviewRepository.findByUser(user);
    }

    @Transactional()
    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }

    public Review findById(Long id) {
        Optional<Review> review = reviewRepository.findById(id);
        if (review.isEmpty()) {
            throw new ReviewNotFoundException("The review with this id wasn't found");
        }

        return review.get();
    }

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

}
