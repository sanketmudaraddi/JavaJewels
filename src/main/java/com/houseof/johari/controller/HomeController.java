package com.houseof.johari.controller;

import com.houseof.johari.model.HomeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/home")
public class HomeController {

    @GetMapping
    public ResponseEntity<HomeResponse> getHome() {
        HomeResponse homeResponse = new HomeResponse();

        // URLs for each category, these would be endpoints to fetch actual data
        homeResponse.setShopByCategoriesLinks(List.of(
                "/api/categories/all",
                "/api/categories/rings",
                "/api/categories/necklaces",
                "/api/categories/bracelets",
                "/api/categories/earrings",
                "/api/categories/others"
        ));

        // URLs for recently viewed products (you can fetch actual products based on user ID)
        homeResponse.setRecentlyViewedLinks(List.of(
                "/api/recently-viewed/1",
                "/api/recently-viewed/2",
                "/api/recently-viewed/3"
        ));

        // URL to fetch new arrivals
        homeResponse.setNewArrivalsLink("/api/products/new-arrivals");

        // URL to fetch best sellers
        homeResponse.setBestSellersLink("/api/products/best-sellers");

        // URLs for shopping by price
        homeResponse.setShopByPriceLinks(List.of(
                "/api/products/price/under-5000",
                "/api/products/price/under-10000",
                "/api/products/price/under-15000"
        ));

        // URLs for shopping by collections (e.g., Wedding, Festivals)
        homeResponse.setShopCollectionsLinks(List.of(
                "/api/products/collections/wedding",
                "/api/products/collections/festivals"
        ));

        return ResponseEntity.ok(homeResponse);
    }
}
