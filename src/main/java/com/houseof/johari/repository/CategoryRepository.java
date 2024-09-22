package com.houseof.johari.repository;

import com.houseof.johari.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {
    // Custom query methods can be added here if necessary
}
