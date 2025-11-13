package com.example.demo_store.dto;

import lombok.Data;
import com.example.demo_store.entity.Role;

@Data
public class UserRegisterRequest {
    private String username;
    private String password;
    private String email;
    private Role role;
}