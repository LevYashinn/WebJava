package com.example.demo_store.repository;

import com.example.demo_store.entity.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameProductContainingIgnoreCase(String keyword);

    List<Product> findByCategory_Id(Long categoryId);

    List<Product> findByCategory_IdAndNameProductContainingIgnoreCase(Long categoryId, String keyword);

}