package com.example.demo_store.mapper;

import com.example.demo_store.dto.PaymentDTO;
import com.example.demo_store.entity.Payment;

public class PaymentMapper {
    public static PaymentDTO toDTO(Payment entity) {
        return new PaymentDTO(
                entity.getId(),
                entity.getOrder().getId(),
                entity.getAmount(),
                entity.getPaymentMethod(),
                entity.getPaymentStatus(),
                entity.getPaymentDate()
        );
    }

    public static Payment toEntity(PaymentDTO dto) {
        Payment payment = new Payment();
        payment.setId(dto.getId());
        payment.setAmount(dto.getAmount());
        payment.setPaymentMethod(dto.getPaymentMethod());
        payment.setPaymentStatus(dto.getPaymentStatus());
        payment.setPaymentDate(dto.getPaymentDate());
        return payment;
    }
}
