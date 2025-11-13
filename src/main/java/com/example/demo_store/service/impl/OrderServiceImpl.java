package com.example.demo_store.service.impl;

import com.example.demo_store.dto.OrderDTO;
import com.example.demo_store.dto.RevenueDTO;
import com.example.demo_store.entity.Order;
import com.example.demo_store.entity.OrderItem;
import com.example.demo_store.entity.Product;
import com.example.demo_store.entity.User;
import com.example.demo_store.mapper.OrderMapper;
import com.example.demo_store.repository.OrderRepository;
import com.example.demo_store.repository.ProductRepository;
import com.example.demo_store.repository.UserRepository;
import com.example.demo_store.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;
    private final UserRepository userRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository,
            OrderMapper orderMapper, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderMapper = orderMapper;
        this.userRepository = userRepository;
    }

    @Override
    public OrderDTO createOrder(OrderDTO dto) {
        Order order = orderMapper.toEntity(dto);

        // Gán user nếu có
        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            order.setUser(user);
        }

        return orderMapper.toDTO(orderRepository.save(order));
    }

    @Override
    public OrderDTO updateOrder(Long id, OrderDTO dto) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        existingOrder.setCustomerName(dto.getCustomerName());
        existingOrder.setShippingAddress(dto.getShippingAddress());
        existingOrder.setOrderDate(dto.getOrderDate());

        // Xóa item cũ và thêm item mới
        existingOrder.getItems().clear();

        List<OrderItem> updatedItems = dto.getItems().stream().map(itemDto -> {
            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setQuantity(itemDto.getQuantity());
            item.setPrice(product.getPriceProduct());
            item.setOrder(existingOrder);
            return item;
        }).collect(Collectors.toList());

        existingOrder.getItems().addAll(updatedItems);

        return orderMapper.toDTO(orderRepository.save(existingOrder));
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        return orderMapper.toDTO(orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found")));
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RevenueDTO> getMonthlyRevenue(int month, int year) {
        List<Object[]> results = orderRepository.getMonthlyRevenueNative(month, year);
        return results.stream()
                .map(r -> new RevenueDTO(
                        ((Number) r[0]).intValue(),
                        ((Number) r[1]).doubleValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getOrdersByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Order> orders = orderRepository.findByUser(user);
        return orders.stream()
                .map(orderMapper::toDTO) // ✅ dùng mapper sẵn có
                .collect(Collectors.toList());
    }

}
