package com.jewels.controller;

import com.jewels.model.Category;
import com.jewels.model.PriceRange;
import com.jewels.model.Product;
import com.jewels.service.CategoryService;
import com.jewels.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/home")
public class HomeController {

    @Autowired
    private CategoryService categoryService; // Service to handle categories
    @Autowired
    private ProductService productService;   // Service to handle products

    // Home page with all sections
    @GetMapping
    public ResponseEntity<Map<String, Object>> getHomePage() {
        Map<String, Object> response = new HashMap<>();

        // Fetch categories
        List<Category> categories = categoryService.getAllCategories();
        response.put("shopByCategories", categories);

        // Fetch recently viewed products
        List<Product> recentlyViewed = productService.getRecentlyViewedProducts();
        response.put("recentlyViewed", recentlyViewed);

        // Fetch new arrivals
        List<Product> newArrivals = productService.getNewArrivals();
        response.put("newArrivals", newArrivals);

        // Fetch best sellers
        List<Product> bestSellers = productService.getBestSellers();
        response.put("bestSellers", bestSellers);

        // Fetch shop by price options
        List<PriceRange> priceRanges = productService.getPriceRanges();
        response.put("shopByPrice", priceRanges);

        // Fetch shop collections
        List<Collection> collections = productService.getShopCollections();
        response.put("shopCollections", collections);

        // Fetch featured products
        List<Product> featuredProducts = productService.getFeaturedProducts();
        response.put("featuredProducts", featuredProducts);

        return ResponseEntity.ok(response);
    }
}
