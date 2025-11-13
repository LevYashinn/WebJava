package com.example.demo_store.service;

import java.util.List;

import com.example.demo_store.dto.OrderDTO;
import com.example.demo_store.dto.RevenueDTO;

public interface OrderService {
    OrderDTO createOrder(OrderDTO dto);

    OrderDTO updateOrder(Long id, OrderDTO dto);

    void deleteOrder(Long id);

    OrderDTO getOrderById(Long id);

    List<OrderDTO> getAllOrders();

    List<RevenueDTO> getMonthlyRevenue(int month, int year); // 🔹 thêm dòng này

    List<OrderDTO> getOrdersByUser(Long userId);

}