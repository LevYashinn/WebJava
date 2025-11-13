package com.example.demo_store.repository;

import com.example.demo_store.dto.ProductReviewSummaryDTO;
import com.example.demo_store.dto.ProductWithReviewSummaryDTO;
import com.example.demo_store.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByProductId(Long productId);

   @Query("SELECT new com.example.demo_store.dto.ProductWithReviewSummaryDTO(" +
       "p.id, p.nameProduct, p.priceProduct, p.imageUrl, " +
       "COALESCE(AVG(r.rating), 0), COUNT(r)) " +
       "FROM Product p LEFT JOIN Review r ON r.product = p " +
       "GROUP BY p.id, p.nameProduct, p.priceProduct, p.imageUrl")
       List<ProductWithReviewSummaryDTO> getAllProductsWithReviewSummary();

    @Query("SELECT new com.example.demo_store.dto.ProductReviewSummaryDTO(" +
           "r.product.id, COALESCE(AVG(r.rating),0), COUNT(r)) " +
           "FROM Review r WHERE r.product.id = :productId GROUP BY r.product.id")
    ProductReviewSummaryDTO getReviewSummaryByProductId(Long productId);
}
