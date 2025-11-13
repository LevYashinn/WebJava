package com.example.demo_store.repository;

import com.example.demo_store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    // Thêm method này để tìm theo email
    Optional<User> findByEmail(String email);
}