package com.example.demo_store.service.impl;

import com.example.demo_store.entity.Cart;
import com.example.demo_store.entity.CartItem;
import com.example.demo_store.entity.Product;
import com.example.demo_store.entity.User;
import com.example.demo_store.repository.CartItemRepository;
import com.example.demo_store.repository.CartRepository;
import com.example.demo_store.repository.ProductRepository;
import com.example.demo_store.service.CartService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    public CartServiceImpl(CartRepository cartRepository,
            ProductRepository productRepository,
            CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public Cart getCartByUser(User user) {
        return cartRepository.findByUser(user).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUser(user);
            return cartRepository.save(newCart);
        });
    }

    @Override
    public Cart addItemToCart(User user, Long productId, int quantity) {
        Cart cart = getCartByUser(user);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Optional<CartItem> existingItemOpt = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingItemOpt.isPresent()) {
            CartItem existingItem = existingItemOpt.get();
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            cart.getItems().add(newItem);
        }

        return cartRepository.save(cart);
    }

    @Override
    public Cart updateCartItemQuantity(User user, Long cartItemId, int quantity) {
        Cart cart = getCartByUser(user);
        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("CartItem not found"));

        if (!item.getCart().getId().equals(cart.getId())) {
            throw new RuntimeException("CartItem does not belong to user cart");
        }

        if (quantity <= 0) {
            cart.getItems().remove(item);
            cartItemRepository.delete(item);
        } else {
            item.setQuantity(quantity);
        }

        return cartRepository.save(cart);
    }

    @Override
    public void removeItemFromCart(User user, Long cartItemId) {
        Cart cart = getCartByUser(user);
        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("CartItem not found"));

        if (!item.getCart().getId().equals(cart.getId())) {
            throw new RuntimeException("CartItem does not belong to user cart");
        }

        cart.getItems().remove(item);
        cartItemRepository.delete(item);
        cartRepository.save(cart);
    }

    @Override
    public void clearCart(User user) {
        Cart cart = getCartByUser(user);
        for (CartItem item : cart.getItems()) {
            cartItemRepository.delete(item);
        }
        cart.getItems().clear();
        cartRepository.save(cart);
    }
}
