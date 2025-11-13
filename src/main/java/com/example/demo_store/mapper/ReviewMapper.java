package com.example.demo_store.mapper;

import com.example.demo_store.dto.ReviewDTO;
import com.example.demo_store.entity.Product;
import com.example.demo_store.entity.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    public ReviewDTO toDTO(Review review) {
        if (review == null)
            return null;

        ReviewDTO dto = new ReviewDTO();
        dto.setId(review.getId());
        dto.setProductId(review.getProduct().getId());
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());
        dto.setCreatedAt(review.getCreatedAt());
        return dto;
    }

    public Review toEntity(ReviewDTO dto, Product product) {
        if (dto == null)
            return null;

        Review review = new Review();
        review.setProduct(product);
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        review.setCreatedAt(dto.getCreatedAt());
        return review;
    }
}
