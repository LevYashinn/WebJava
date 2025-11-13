package com.example.demo_store.service;

import java.util.Map;

public interface AuthService {
    Map<String, Object> login(String username, String password);

    boolean sendResetPasswordEmail(String email);

    boolean resetPassword(String token, String newPassword);
}