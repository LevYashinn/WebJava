package com.example.demo_store.mapper;

import com.example.demo_store.dto.ProductDTO;
import com.example.demo_store.dto.ReviewDTO;
import com.example.demo_store.entity.Category;
import com.example.demo_store.entity.Product;
import com.example.demo_store.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    private final ReviewMapper reviewMapper; // thêm field này

    public ProductMapper(ReviewMapper reviewMapper) { // inject qua constructor
        this.reviewMapper = reviewMapper;
    }

    // Chuyển Product -> ProductDTO
    public ProductDTO toDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setNameProduct(product.getNameProduct());
        dto.setDescriptionProduct(product.getDescriptionProduct());
        dto.setPriceProduct(product.getPriceProduct());
        dto.setBrand(product.getBrand());
        dto.setQuantity(product.getQuantity());
        dto.setImageUrl(product.getImageUrl());

        // Map category
        if (product.getCategory() != null) {
            dto.setCategoryId(product.getCategory().getId());
            dto.setCategoryName(product.getCategory().getName());
        }

        // Map order IDs nếu cần
        if (product.getOrderItems() != null) {
            dto.setOrderIds(product.getOrderItems().stream()
                    .map(oi -> oi.getOrder().getId())
                    .collect(Collectors.toList()));
        }
        // Map reviews
        List<ReviewDTO> reviewDTOS = product.getReviews() != null
                ? product.getReviews().stream()
                        .map(reviewMapper::toDTO) // <--- reviewMapper phải có toDTO(Review)
                        .collect(Collectors.toList())
                : List.of();
        dto.setReviews(reviewDTOS);

        return dto;
    }

    // Chuyển ProductDTO -> Product
    public Product toEntity(ProductDTO dto, CategoryRepository categoryRepository) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setNameProduct(dto.getNameProduct());
        product.setDescriptionProduct(dto.getDescriptionProduct());
        product.setPriceProduct(dto.getPriceProduct());
        product.setBrand(dto.getBrand());
        product.setQuantity(dto.getQuantity());
        product.setImageUrl(dto.getImageUrl());

        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId())

                    .orElseThrow(() -> new RuntimeException("Category not found"));
            product.setCategory(category);
        }

        return product;
    }
}
