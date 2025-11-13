package com.example.demo_store.service;

import com.example.demo_store.entity.Cart;
import com.example.demo_store.entity.User;

public interface CartService {
    Cart getCartByUser(User user);

    Cart addItemToCart(User user, Long productId, int quantity);

    Cart updateCartItemQuantity(User user, Long cartItemId, int quantity);

    void removeItemFromCart(User user, Long cartItemId);

    void clearCart(User user);
}
