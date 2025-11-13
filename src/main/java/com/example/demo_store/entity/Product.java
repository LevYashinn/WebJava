package com.example.demo_store.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameProduct;
    private String descriptionProduct;
    private Double priceProduct;

    // Các thuộc tính tùy chọn
    private String brand;
    private int quantity;

    // nhiều ảnh
    // private List<ProductImage> images;

    // Đường dẫn hoặc tên ảnh sản phẩm
    @Column(name = "images")
    private String imageUrl;

    // 1 Product có thể xuất hiện trong nhiều OrderItem
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<OrderItem> orderItems = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // 1 Product có nhiều Review
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();
}
