package com.example.demo_store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO extends RepresentationModel<ProductDTO> {
    private Long id;
    private String nameProduct;
    private String descriptionProduct;
    private Double priceProduct;
    private String brand;
    private int quantity;
    private String imageUrl;
    // Danh sách ID của các đơn hàng liên quan (optional)
    private List<Long> orderIds;
    // Thông tin category đơn giản
    private Long categoryId;
    private String categoryName;
    private List<ReviewDTO> reviews; // Danh sách đánh giá
}