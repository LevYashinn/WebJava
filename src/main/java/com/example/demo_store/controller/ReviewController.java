package com.example.demo_store.controller;

import com.example.demo_store.dto.ProductReviewSummaryDTO;
import com.example.demo_store.dto.ProductWithReviewSummaryDTO;
import com.example.demo_store.dto.ReviewDTO;
import com.example.demo_store.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public List<ReviewDTO> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/product/{productId}")
    public List<ReviewDTO> getReviewsByProduct(@PathVariable Long productId) {
        return reviewService.getReviewsByProductId(productId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(reviewService.getReviewById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ReviewDTO createReview(@RequestBody ReviewDTO reviewDTO) {
        return reviewService.createReview(reviewDTO);
    }

    @PutMapping("/{id}")
    public ReviewDTO updateReview(@PathVariable Long id, @RequestBody ReviewDTO reviewDTO) {
        return reviewService.updateReview(id, reviewDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/product/{productId}/summary")
    public ResponseEntity<ProductReviewSummaryDTO> getReviewSummary(@PathVariable Long productId) {
        ProductReviewSummaryDTO summary = reviewService.getReviewSummaryByProductId(productId);
        if (summary == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(summary);
    }

    @GetMapping("/summary/all-products")
    public List<ProductWithReviewSummaryDTO> getAllProductsWithReviewSummary() {
        return reviewService.getAllProductsWithReviewSummary();
    }
}
