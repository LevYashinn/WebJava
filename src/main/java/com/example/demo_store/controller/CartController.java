package com.example.demo_store.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.example.demo_store.entity.Cart;
import com.example.demo_store.entity.User;
import com.example.demo_store.repository.UserRepository;
import com.example.demo_store.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    private final UserRepository userRepository;

    public CartController(CartService cartService, UserRepository userRepository) {
        this.cartService = cartService;
        this.userRepository = userRepository;
    }

    // Lấy giỏ hàng hiện tại (nếu đăng nhập)
    @GetMapping
    public ResponseEntity<Cart> getCart(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        return ResponseEntity.ok(cartService.getCartByUser(user));
    }

    // Thêm sản phẩm vào giỏ
    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam Long productId,
            @RequestParam int quantity) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        return ResponseEntity.ok(cartService.addItemToCart(user, productId, quantity));
    }

    @PutMapping("/update")
    public ResponseEntity<Cart> updateCartItem(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam Long cartItemId,
            @RequestParam int quantity) {

        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart updatedCart = cartService.updateCartItemQuantity(user, cartItemId, quantity);

        return ResponseEntity.ok(updatedCart);
    }

    // Xóa sản phẩm khỏi giỏ
    @DeleteMapping("/remove/{cartItemId}")
    public ResponseEntity<Void> removeItem(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long cartItemId) {

        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        cartService.removeItemFromCart(user, cartItemId); // truyền user
        return ResponseEntity.noContent().build();
    }

    // Xóa toàn bộ giỏ hàng
    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearCart(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        cartService.clearCart(user);
        return ResponseEntity.noContent().build();
    }
}