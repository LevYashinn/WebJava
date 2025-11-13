package com.example.demo_store.repository;

import com.example.demo_store.entity.Category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByParentIsNull();

    List<Category> findByParentId(Long parentId);
}
