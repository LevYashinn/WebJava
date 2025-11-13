package com.example.demo_store.mapper;

import com.example.demo_store.dto.CartItemDTO;
import com.example.demo_store.entity.Cart;
import com.example.demo_store.entity.CartItem;
import com.example.demo_store.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class CartItemMapper {

    public CartItemDTO toDTO(CartItem item) {
        CartItemDTO dto = new CartItemDTO();
        dto.setId(item.getId());
        dto.setProductId(item.getProduct().getId());
        dto.setProductName(item.getProduct().getNameProduct());
        dto.setQuantity(item.getQuantity());
        dto.setPricePerItem(item.getProduct().getPriceProduct());
        return dto;
    }

    public CartItem toEntity(CartItemDTO dto, Product product, Cart cart) {
        CartItem item = new CartItem();
        item.setId(dto.getId());
        item.setProduct(product);
        item.setCart(cart);
        item.setQuantity(dto.getQuantity());
        return item;
    }
}
