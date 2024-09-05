package com.houseof.johari.repository;

import com.houseof.johari.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {
    // Custom query methods if needed
}
