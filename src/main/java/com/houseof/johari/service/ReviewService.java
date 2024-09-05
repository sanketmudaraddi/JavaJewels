package com.houseof.johari.service;

import com.houseof.johari.model.Review;
import com.houseof.johari.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    public Review findById(String id) {
        Optional<Review> review = reviewRepository.findById(id);
        return review.orElse(null);
    }

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public Review update(String id, Review review) {
        if (reviewRepository.existsById(id)) {
            review.setId(id);
            return reviewRepository.save(review);
        }
        return null;
    }

    public boolean delete(String id) {
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
