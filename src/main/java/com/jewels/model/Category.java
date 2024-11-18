package com.jewels.model;

import lombok.Data;

import java.util.List;

@Data
public class Category {
    private String id;
    private String name; // Main category name like "Rings", "Necklaces & Pendants"
    private List<SubCategory> subCategories; // List of subcategories under this category
    private List<String> priceRanges; // Price filters
    private List<String> materials; // Material filters like "gold", "silver"
    private List<String> occasions; // Occasion filters

    // Getters and Setters

    @Data
    public static class SubCategory {
        private String name; // For Men, For Women
        private List<String> applicableFilters; // Filters applicable for this subcategory
        // Getters and Setters
    }
}
