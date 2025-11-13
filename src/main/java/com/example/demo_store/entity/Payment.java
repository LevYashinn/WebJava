package com.example.demo_store.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    private String paymentMethod;
    private String paymentStatus;
    private LocalDateTime paymentDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    private Order order;

    @PrePersist
    public void prePersist() {
        if (paymentDate == null) {
            paymentDate = LocalDateTime.now();
        }
    }
}
