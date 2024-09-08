package com.houseof.johari.service;

import com.houseof.johari.model.Product;
import com.houseof.johari.repository.ProductRepository;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

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

    public List<Product> searchProducts(String keyword) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").regex(keyword, "i"));
        return mongoTemplate.find(query, Product.class);
    }


    public List<Product> filterProducts(String category, String brand, Double minPrice, Double maxPrice, String material, String size, List<String> colors, Boolean inStock) {
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

        // Execute the query and return the filtered list of products
        return mongoTemplate.find(query, Product.class);
    }
}