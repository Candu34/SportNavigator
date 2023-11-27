package com.example.sportnavigator.Controllers;


import com.example.sportnavigator.DTO.ReviewDTO;
import com.example.sportnavigator.DTO.SportCourtDTO;
import com.example.sportnavigator.Models.Review;
import com.example.sportnavigator.Models.SportCourt;
import com.example.sportnavigator.Service.ReviewService;
import com.example.sportnavigator.mapper.MapStructMapper;
import com.example.sportnavigator.util.exceptions.CourtNotCreatedException;
import com.example.sportnavigator.util.exceptions.ReviewNotCeatedException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final MapStructMapper mapStructMapper;


    @GetMapping()
    public List<ReviewDTO> findAll() {
        List<Review> reviews = reviewService.findAll();
        List<ReviewDTO> reviewDTOS = new ArrayList<>();
        for (Review review : reviews) {
            reviewDTOS.add(mapStructMapper.ReviewToReviewDTO(review));
        }
        return reviewDTOS;
    }

    @GetMapping("/{id}")
    public ReviewDTO findOne(@PathVariable Long id) {
        Review review = reviewService.findById(id);
        return mapStructMapper.ReviewToReviewDTO(review);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<HttpStatus> save(@RequestBody @Valid ReviewDTO reviewDTO,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }

            throw new ReviewNotCeatedException(errorMsg.toString());
        }
        Review review = mapStructMapper.ReviewDTOToReview(reviewDTO);
        reviewService.save(review);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {

        reviewService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
