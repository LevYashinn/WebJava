package com.example.demo_store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private Long id; // ID review
    private Long productId; // ID sản phẩm được đánh giá
    private Long customerId; // ID khách hàng đánh giá
    private String customerName; // Tên khách hàng (option, để hiển thị)
    private int rating; // Điểm đánh giá (1-5)
    private String comment; // Nội dung đánh giá
    private LocalDateTime createdAt; // Ngày tạo
}
