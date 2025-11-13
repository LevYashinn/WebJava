package com.example.demo_store.service.impl;

import com.example.demo_store.dto.PaymentDTO;
import com.example.demo_store.entity.Order;
import com.example.demo_store.entity.Payment;
import com.example.demo_store.mapper.PaymentMapper;
import com.example.demo_store.repository.OrderRepository;
import com.example.demo_store.repository.PaymentRepository;
import com.example.demo_store.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository, OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        return paymentRepository.findAll().stream()
                .map(PaymentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentDTO getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .map(PaymentMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    @Override
    public PaymentDTO createPayment(PaymentDTO dto) {
        Payment payment = PaymentMapper.toEntity(dto);

        Order order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        payment.setOrder(order);

        return PaymentMapper.toDTO(paymentRepository.save(payment));
    }

    @Override
    public PaymentDTO getPaymentByOrderId(Long orderId) {
        Payment payment = paymentRepository.findByOrderId(orderId);
        if (payment == null) {
            throw new RuntimeException("Payment not found for order ID: " + orderId);
        }
        return PaymentMapper.toDTO(payment);
    }
}
