package com.houseof.johari.model;


import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
public class HomeResponse {

    private List<Category> shopByCategories;
    private List<Product> recentlyViewed;
    private List<Product> newArrivals;
    private List<Product> bestSellers;
    private List<PriceRange> shopByPrice;
    private List<Collection> shopCollections;
    private List<Product> featuredProducts;

    // Getters and setters

    public List<Category> getShopByCategories() {
        return shopByCategories;
    }

    public void setShopByCategories(List<Category> shopByCategories) {
        this.shopByCategories = shopByCategories;
    }

    public List<Product> getRecentlyViewed() {
        return recentlyViewed;
    }

    public void setRecentlyViewed(List<Product> recentlyViewed) {
        this.recentlyViewed = recentlyViewed;
    }

    public List<Product> getNewArrivals() {
        return newArrivals;
    }

    public void setNewArrivals(List<Product> newArrivals) {
        this.newArrivals = newArrivals;
    }

    public List<Product> getBestSellers() {
        return bestSellers;
    }

    public void setBestSellers(List<Product> bestSellers) {
        this.bestSellers = bestSellers;
    }

    public List<PriceRange> getShopByPrice() {
        return shopByPrice;
    }

    public void setShopByPrice(List<PriceRange> shopByPrice) {
        this.shopByPrice = shopByPrice;
    }

    public List<Collection> getShopCollections() {
        return shopCollections;
    }

    public void setShopCollections(List<Collection> shopCollections) {
        this.shopCollections = shopCollections;
    }

    public List<Product> getFeaturedProducts() {
        return featuredProducts;
    }

    public void setFeaturedProducts(List<Product> featuredProducts) {
        this.featuredProducts = featuredProducts;
    }
}
