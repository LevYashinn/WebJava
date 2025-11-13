package com.example.demo_store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductReviewSummaryDTO {
    private Long productId;
    private double averageRating; // Điểm trung bình
    private long totalReviews;    // Tổng số đánh giá

}
