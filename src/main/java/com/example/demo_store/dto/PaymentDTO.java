package com.example.demo_store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * Data Transfer Object cho Payment (Thanh toán)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
    private Long id; // ID của thanh toán
    private Long orderId; // Mã đơn hàng liên kết
    private Double amount; // Số tiền thanh toán
    private String paymentMethod; // Hình thức thanh toán (COD, Momo, ZaloPay, v.v.)
    private String paymentStatus; // Trạng thái thanh toán (PENDING, SUCCESS, FAILED)
    private LocalDateTime paymentDate; // Thời gian thanh toán
}
