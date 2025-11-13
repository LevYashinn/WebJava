package com.example.demo_store.service;

import com.example.demo_store.dto.PaymentDTO;
import java.util.List;

public interface PaymentService {
    List<PaymentDTO> getAllPayments();

    PaymentDTO getPaymentById(Long id);

    PaymentDTO createPayment(PaymentDTO paymentDTO);

    PaymentDTO getPaymentByOrderId(Long orderId);
}