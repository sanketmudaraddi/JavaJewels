package com.houseof.johari.repository;

import com.houseof.johari.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByCategory(String category);
    List<Product> findByCategoryAndGender(String category, String gender);
    List<Product> findByCategoryAndPriceLessThanEqual(String category, double price);
    List<Product> findByCategoryAndMaterial(String category, String material);
    List<Product> findByCategoryAndOccasion(String category, String occasion);
    List<Product> findByCategoryAndGenderAndPriceLessThanEqualAndMaterialAndOccasion(String category, String gender, Double price, String material, String occasion);
    // Fetch all products marked as best sellers
    List<Product> findByBestSellerTrue();

    // Fetch all products with an ongoing offer
    List<Product> findByHasOfferTrue();
}
