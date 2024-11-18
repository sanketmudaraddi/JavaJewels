package com.jewels.model;

public class PriceRange {

    private String priceRange;
    private String categoryId;

    public PriceRange(String priceRange, String categoryId) {
        this.priceRange = priceRange;
        this.categoryId = categoryId;
    }

    // Getters and setters
    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
