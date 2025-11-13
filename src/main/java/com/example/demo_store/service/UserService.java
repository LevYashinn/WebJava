package com.example.demo_store.service;

import com.example.demo_store.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO register(UserDTO dto, String rawPassword);
    List<UserDTO> getAllUsers();
    UserDTO updateUser(Long id, UserDTO dto);
    void deleteUser(Long id);
    UserDTO getUserById(Long id);
}