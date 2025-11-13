package com.example.demo_store.mapper;

import com.example.demo_store.dto.CartDTO;
import com.example.demo_store.dto.CartItemDTO;
import com.example.demo_store.entity.Cart;
import com.example.demo_store.entity.CartItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {

    public CartItemDTO toCartItemDTO(CartItem item) {
        return new CartItemDTO(
                item.getId(),
                item.getProduct().getId(),
                item.getProduct().getNameProduct(),
                item.getQuantity(),
                item.getProduct().getPriceProduct()
        );
    }

    public CartDTO toCartDTO(Cart cart) {
        List<CartItemDTO> itemDTOs = cart.getItems().stream()
                .map(this::toCartItemDTO)
                .collect(Collectors.toList());

        double total = itemDTOs.stream()
                .mapToDouble(i -> i.getQuantity() * i.getPricePerItem())
                .sum();

        return new CartDTO(cart.getId(), itemDTOs, total);
    }
}
