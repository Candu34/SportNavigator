package com.example.sportnavigator.Service;


import com.example.sportnavigator.Models.Review;
import com.example.sportnavigator.Models.SportCourt;
import com.example.sportnavigator.Models.User;
import com.example.sportnavigator.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Transactional()
    public void save(Review review){
        reviewRepository.save(review);
    }

    public List<Review> findBySportCourt(SportCourt sportCourt){
        return reviewRepository.findBySportCourt(sportCourt);
    }

    public List<Review> findByUser(User user){
        return reviewRepository.findByUser(user);
    }

    @Transactional()
    public void deleteById(Long id){
        reviewRepository.deleteById(id);
    }

    public Review findById(Long id){
        return reviewRepository.findById(id).orElse(null); //TODO exception handler
    }

    public List<Review> findAll(){
        return reviewRepository.findAll();
    }

}
