package com.houseof.johari.repository;

import com.houseof.johari.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Collection;
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

    // Custom query to fetch recently viewed products
    @Query("SELECT p FROM Product p WHERE p.recentlyViewed = true") // Replace with the appropriate condition
    List<Product> findRecentlyViewed();

    // Custom query to fetch new arrivals
    @Query("SELECT p FROM Product p WHERE p.newArrival = true") // Replace with the appropriate condition
    List<Product> findNewArrivals();

    // Custom query to fetch best sellers
    @Query("SELECT p FROM Product p WHERE p.bestSeller = true") // Replace with the appropriate condition
    List<Product> findBestSellers();

    // Custom query to fetch featured products
    @Query("SELECT p FROM Product p WHERE p.featured = true") // Replace with the appropriate condition
    List<Product> findFeaturedProducts();

    // Custom query to fetch collections (Assuming there's a Collection entity related to Product)
    @Query("SELECT DISTINCT c FROM Collection c JOIN c.products p") // Replace with appropriate JPQL
    List<Collection> findCollections();


    List<Product> findByPriceLessThan(double price);
}
