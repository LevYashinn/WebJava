package com.example.demo_store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductWithReviewSummaryDTO {
    private Long id;
    private String nameProduct;
    private Double priceProduct;
    private String imageUrl;
    private Double avgRating;
    private Long reviewCount;

}
