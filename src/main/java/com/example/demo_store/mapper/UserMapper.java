package com.example.demo_store.mapper;

import com.example.demo_store.dto.UserDTO;
import com.example.demo_store.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }

    public User toEntity(UserDTO dto, String encodedPassword) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(encodedPassword);
        user.setRole(dto.getRole());
        return user;
    }
}
