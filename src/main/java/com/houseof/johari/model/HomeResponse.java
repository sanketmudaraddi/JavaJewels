package com.houseof.johari.model;


import lombok.Data;

import java.util.List;

@Data
public class HomeResponse {
    private List<String> shopByCategoriesLinks;
    private List<String> recentlyViewedLinks;
    private String newArrivalsLink;
    private String bestSellersLink;
    private List<String> shopByPriceLinks;
    private List<String> shopCollectionsLinks;

    // Getters and Setters

}
