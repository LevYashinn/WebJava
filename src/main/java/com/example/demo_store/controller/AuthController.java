package com.example.demo_store.controller;

import com.example.demo_store.dto.ForgotPasswordRequest;
import com.example.demo_store.dto.LoginRequest;
import com.example.demo_store.dto.ResetPasswordRequest;
import com.example.demo_store.service.AuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Map<String, Object> response = authService.login(
                    request.getUsername(),
                    request.getPassword());
            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + response.get("token"))
                    .body(response);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username or password");
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        boolean result = authService.sendResetPasswordEmail(request.getEmail());
        if (result)
            return ResponseEntity.ok("Email reset mật khẩu đã được gửi.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email không tồn tại.");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest request) {
        boolean result = authService.resetPassword(request.getToken(), request.getNewPassword());
        if (result)
            return ResponseEntity.ok("Đổi mật khẩu thành công.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token không hợp lệ hoặc hết hạn.");
    }
}
