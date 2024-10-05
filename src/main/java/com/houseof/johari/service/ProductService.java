package com.houseof.johari.service;

import com.houseof.johari.model.PriceRange;
import com.houseof.johari.model.Product;
import com.houseof.johari.model.SearchHistory;
import com.houseof.johari.repository.ProductRepository;
import com.houseof.johari.repository.SearchHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Predicate;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private SearchHistoryRepository searchHistoryRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product findById(String id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    // Find all products with pagination and sorting
    public List<Product> findAll(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return productRepository.findAll(pageable).getContent();
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Product update(String id, Product product) {
        if (productRepository.existsById(id)) {
            product.setId(id);
            return productRepository.save(product);
        }
        return null;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public boolean delete(String id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Product> searchProducts(String userId, String keyword) {
        // Save search history
        SearchHistory history = new SearchHistory();
        history.setUserId(userId);
        history.setSearchTerm(keyword);
        history.setTimestamp(LocalDateTime.now());
        searchHistoryRepository.save(history);

        // Perform search
        Query query = new Query();
        query.addCriteria(Criteria.where("name").regex(keyword, "i"));
        return mongoTemplate.find(query, Product.class);
    }

    public List<SearchHistory> getRecentSearches(String userId) {
        return searchHistoryRepository.findTop10ByUserIdOrderByTimestampDesc(userId);
    }


    public List<Product> filterProducts(String category, String brand, Double minPrice, Double maxPrice, String material, String size, List<String> colors, Boolean inStock, String priceToggle) {
        Query query = new Query();

        // Check if a category filter is provided and add it to the query
        if (category != null && !category.isEmpty()) {
            query.addCriteria(Criteria.where("category").is(category));
        }

        // Check if a brand filter is provided and add it to the query
        if (brand != null && !brand.isEmpty()) {
            query.addCriteria(Criteria.where("brand").is(brand));
        }

        // Check if a minimum price filter is provided and add it to the query
        if (minPrice != null) {
            query.addCriteria(Criteria.where("price").gte(minPrice));
        }

        // Check if a maximum price filter is provided and add it to the query
        if (maxPrice != null) {
            query.addCriteria(Criteria.where("price").lte(maxPrice));
        }

        // Check if a material filter is provided and add it to the query
        if (material != null && !material.isEmpty()) {
            query.addCriteria(Criteria.where("material").is(material));
        }

        // Check if a size filter is provided and add it to the query
        if (size != null && !size.isEmpty()) {
            query.addCriteria(Criteria.where("size").is(size));
        }

        // Check if a list of colors is provided and add it to the query
        if (colors != null && !colors.isEmpty()) {
            query.addCriteria(Criteria.where("colors").in(colors));
        }

        // Check if the in-stock filter is provided and add it to the query
        if (inStock != null) {
            query.addCriteria(Criteria.where("inStock").is(inStock));
        }

        // Price filter with toggle
        if (priceToggle != null) {
            switch (priceToggle) {
                case "low":
                    if (minPrice != null) {
                        query.addCriteria(Criteria.where("price").gte(minPrice));
                    }
                    break;
                case "high":
                    if (maxPrice != null) {
                        query.addCriteria(Criteria.where("price").lte(maxPrice));
                    }
                    break;
                case "range":
                    if (minPrice != null && maxPrice != null) {
                        query.addCriteria(Criteria.where("price").gte(minPrice).lte(maxPrice));
                    }
                    break;
            }
        }

        // Execute the query and return the filtered list of products
        return mongoTemplate.find(query, Product.class);
    }

    //Get products by category
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    // Get products by category and gender
    public List<Product> getProductsByCategoryAndGender(String category, String gender) {
        return productRepository.findByCategoryAndGender(category, gender);
    }

    // Get products by category and price (under a certain price)
    public List<Product> getProductsByCategoryAndPrice(String category, double price) {
        return productRepository.findByCategoryAndPriceLessThanEqual(category, price);
    }

    // Get products by category and material
    public List<Product> getProductsByCategoryAndMaterial(String category, String material) {
        return productRepository.findByCategoryAndMaterial(category, material);
    }

    // Get products by category and occasion
    public List<Product> getProductsByCategoryAndOccasion(String category, String occasion) {
        return productRepository.findByCategoryAndOccasion(category, occasion);
    }

    // Get products by combining multiple filters
    public List<Product> getProductsByMultipleFilters(String category, String gender, Double price, String material, String occasion) {
        // This is a simplified example combining category, gender, price, material, and occasion
        if (price != null && gender != null) {
            return productRepository.findByCategoryAndGenderAndPriceLessThanEqualAndMaterialAndOccasion(
                    category, gender, price, material, occasion);
        } else if (price != null) {
            return productRepository.findByCategoryAndPriceLessThanEqual(category, price);
        } else if (gender != null) {
            return productRepository.findByCategoryAndGender(category, gender);
        } else {
            return productRepository.findByCategory(category);
        }
    }

//    // Get all Best Seller products
//    public List<Product> getBestSellers() {
//        return productRepository.findByBestSellerTrue();
//    }

    // Get all products with Offers
    public List<Product> getProductsWithOffers() {
        return productRepository.findByHasOfferTrue();
    }
    // Fetch recently viewed products
    public List<Product> getRecentlyViewedProducts() {
        // Implement logic to fetch recently viewed products
        return productRepository.findRecentlyViewed();
    }

    // Fetch new arrivals
    public List<Product> getNewArrivals() {
        // Implement logic to fetch new arrival products
        return productRepository.findNewArrivals();
    }

    // Fetch best sellers
    public List<Product> getBestSellers() {
        // Implement logic to fetch best sellers
        return productRepository.findBestSellers();
    }

    // Fetch price ranges
    public List<PriceRange> getPriceRanges() {
        // Implement logic to return available price ranges
        return Arrays.asList(
                new PriceRange("Under 5000", "price-1"),
                new PriceRange("Under 10000", "price-2"),
                new PriceRange("Under 15000", "price-3")
        );
    }

    // Fetch shop collections
    public List<Collection> getShopCollections() {
        // Implement logic to fetch collections
        return productRepository.findCollections();
    }

    // Fetch featured products for home page
    public List<Product> getFeaturedProducts() {
        // Implement logic to fetch featured products
        return productRepository.findFeaturedProducts();
    }
}

