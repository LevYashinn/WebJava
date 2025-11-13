package com.example.demo_store.service.impl;

import com.example.demo_store.dto.ProductReviewSummaryDTO;
import com.example.demo_store.dto.ProductWithReviewSummaryDTO;
import com.example.demo_store.dto.ReviewDTO;

import com.example.demo_store.entity.Product;
import com.example.demo_store.entity.Review;
import com.example.demo_store.mapper.ReviewMapper;

import com.example.demo_store.repository.ProductRepository;
import com.example.demo_store.repository.ReviewRepository;
import com.example.demo_store.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    private final ReviewMapper reviewMapper;

    public ReviewServiceImpl(ReviewRepository reviewRepository,
            ProductRepository productRepository,

            ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public List<ReviewDTO> getAllReviews() {
        return reviewRepository.findAll().stream()
                .map(reviewMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> getReviewsByProductId(Long productId) {
        return reviewRepository.findByProductId(productId).stream()
                .map(reviewMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewDTO getReviewById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        return reviewMapper.toDTO(review);
    }

    @Override
    public ReviewDTO createReview(ReviewDTO reviewDTO) {
        Product product = productRepository.findById(reviewDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Review review = reviewMapper.toEntity(reviewDTO, product);
        return reviewMapper.toDTO(reviewRepository.save(review));
    }

    @Override
    public ReviewDTO updateReview(Long id, ReviewDTO reviewDTO) {
        Review existing = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        existing.setRating(reviewDTO.getRating());
        existing.setComment(reviewDTO.getComment());
        return reviewMapper.toDTO(reviewRepository.save(existing));
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public ProductReviewSummaryDTO getReviewSummaryByProductId(Long productId) {
        return reviewRepository.getReviewSummaryByProductId(productId);
    }

    @Override
    public List<ProductWithReviewSummaryDTO> getAllProductsWithReviewSummary() {
        return reviewRepository.getAllProductsWithReviewSummary();
    }
}
