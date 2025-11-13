package com.example.demo_store.service;

import com.example.demo_store.dto.ProductReviewSummaryDTO;
import com.example.demo_store.dto.ProductWithReviewSummaryDTO;
import com.example.demo_store.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {

    List<ReviewDTO> getAllReviews();

    List<ReviewDTO> getReviewsByProductId(Long productId);

    ReviewDTO getReviewById(Long id);

    ReviewDTO createReview(ReviewDTO reviewDTO);

    ReviewDTO updateReview(Long id, ReviewDTO reviewDTO);

    void deleteReview(Long id);

    ProductReviewSummaryDTO getReviewSummaryByProductId(Long productId);

    List<ProductWithReviewSummaryDTO> getAllProductsWithReviewSummary();
}
