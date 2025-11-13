package com.example.demo_store.controller;

import com.example.demo_store.dto.PaymentDTO;
import com.example.demo_store.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // 🔹 Lấy danh sách tất cả thanh toán
    @GetMapping
    public ResponseEntity<List<PaymentDTO>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    // 🔹 Lấy thanh toán theo ID
    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable Long id) {
        PaymentDTO dto = paymentService.getPaymentById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    // 🔹 Tạo thanh toán mới
    @PostMapping
    public ResponseEntity<PaymentDTO> createPayment(@RequestBody PaymentDTO dto) {
        PaymentDTO created = paymentService.createPayment(dto);
        return ResponseEntity.status(201).body(created);
    }

    // 🔹 Lấy thanh toán theo ID đơn hàng
    @GetMapping("/order/{orderId}")
    public ResponseEntity<PaymentDTO> getPaymentByOrderId(@PathVariable Long orderId) {
        PaymentDTO dto = paymentService.getPaymentByOrderId(orderId);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }
}
