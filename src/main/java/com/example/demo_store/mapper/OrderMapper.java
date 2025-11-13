package com.example.demo_store.mapper;

import com.example.demo_store.dto.OrderDTO;
import com.example.demo_store.dto.OrderItemDTO;
import com.example.demo_store.entity.Order;
import com.example.demo_store.entity.OrderItem;
import com.example.demo_store.entity.Product;
import com.example.demo_store.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    private final ProductRepository productRepository;

    public OrderMapper(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Chuyển DTO → Entity
    public Order toEntity(OrderDTO dto) {
        Order order = new Order();
        order.setId(dto.getId());
        order.setCustomerName(dto.getCustomerName());
        order.setShippingAddress(dto.getShippingAddress());
        order.setOrderDate(dto.getOrderDate());
        order.setStatus(dto.getStatus());

        List<OrderItem> items = dto.getItems().stream().map(itemDto -> {
            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setQuantity(itemDto.getQuantity());
            item.setPrice(product.getPriceProduct());
            item.setOrder(order);
            return item;
        }).collect(Collectors.toList());

        order.setItems(items);
        return order;
    }

    // Chuyển Entity → DTO (hiển thị trên frontend)
    public OrderDTO toDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setCustomerName(order.getCustomerName());
        dto.setShippingAddress(order.getShippingAddress());
        dto.setOrderDate(order.getOrderDate());
        dto.setStatus(order.getStatus());

        // Tính tổng tiền của đơn
        double total = order.getItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        dto.setTotalPrice(total);

        // Map items đầy đủ: productId, quantity, price
        List<OrderItemDTO> itemsDto = order.getItems().stream()
                .map(item -> new OrderItemDTO(
                        item.getProduct().getId(),
                        item.getProduct() != null ? item.getProduct().getNameProduct() : "",
                        item.getQuantity(),
                        item.getPrice()))
                .collect(Collectors.toList());
        dto.setItems(itemsDto);

        return dto;
    }
}
