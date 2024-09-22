package com.houseof.johari.controller;

import com.houseof.johari.model.Product;
import com.houseof.johari.model.SearchHistory;
import com.houseof.johari.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.save(product);
        return ResponseEntity.ok(savedProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") String id) {
        Product product = productService.findById(id);
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
        Product updatedProduct = productService.update(id, product);
        return updatedProduct != null ? ResponseEntity.ok(updatedProduct) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") String id) {
        boolean deleted = productService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String userId, @RequestParam String keyword) {
        List<Product> products = productService.searchProducts(userId, keyword);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/recent-searches")
    public ResponseEntity<List<SearchHistory>> getRecentSearches(@RequestParam String userId) {
        List<SearchHistory> recentSearches = productService.getRecentSearches(userId);
        return ResponseEntity.ok(recentSearches);
    }


    @GetMapping("/filter")
    public ResponseEntity<List<Product>> filterProducts(@RequestParam(required = false) String category,
                                                        @RequestParam(required = false) String brand,
                                                        @RequestParam(required = false) Double minPrice,
                                                        @RequestParam(required = false) Double maxPrice,
                                                        @RequestParam(required = false) String material,
                                                        @RequestParam(required = false) String size,
                                                        @RequestParam(required = false) List<String> colors,
                                                        @RequestParam(required = false) Boolean inStock,
                                                        @RequestParam(required = false) String priceToogle) {
        // Call the filter method in the service with all parameters
        List<Product> products = productService.filterProducts(category, brand, minPrice, maxPrice, material, size, colors, inStock, priceToogle);

        // Return the filtered products as a response
        return ResponseEntity.ok(products);
    }
    // Get products by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        List<Product> products = productService.getProductsByCategory(category);
        return ResponseEntity.ok(products);
    }

    // Get products by category and gender
    @GetMapping("/category/{category}/gender/{gender}")
    public ResponseEntity<List<Product>> getProductsByCategoryAndGender(@PathVariable String category, @PathVariable String gender) {
        List<Product> products = productService.getProductsByCategoryAndGender(category, gender);
        return ResponseEntity.ok(products);
    }

    // Get products by category and price
    @GetMapping("/category/{category}/price/{price}")
    public ResponseEntity<List<Product>> getProductsByCategoryAndPrice(@PathVariable String category, @PathVariable double price) {
        List<Product> products = productService.getProductsByCategoryAndPrice(category, price);
        return ResponseEntity.ok(products);
    }

    // Get products by category and material
    @GetMapping("/category/{category}/material/{material}")
    public ResponseEntity<List<Product>> getProductsByCategoryAndMaterial(@PathVariable String category, @PathVariable String material) {
        List<Product> products = productService.getProductsByCategoryAndMaterial(category, material);
        return ResponseEntity.ok(products);
    }

    // Get products by category and occasion
    @GetMapping("/category/{category}/occasion/{occasion}")
    public ResponseEntity<List<Product>> getProductsByCategoryAndOccasion(@PathVariable String category, @PathVariable String occasion) {
        List<Product> products = productService.getProductsByCategoryAndOccasion(category, occasion);
        return ResponseEntity.ok(products);
    }

    // Get Best Sellers
    @GetMapping("/best-sellers")
    public ResponseEntity<List<Product>> getBestSellers() {
        List<Product> bestSellers = productService.getBestSellers();
        return ResponseEntity.ok(bestSellers);
    }

    // Get Products with Offers
    @GetMapping("/offers")
    public ResponseEntity<List<Product>> getProductsWithOffers() {
        List<Product> productsWithOffers = productService.getProductsWithOffers();
        return ResponseEntity.ok(productsWithOffers);
    }
}
