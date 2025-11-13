package com.example.demo_store.service;

import com.example.demo_store.dto.CategoryDTO;
import com.example.demo_store.entity.Category;

import java.util.List;

public interface CategoryService {
    CategoryDTO create(CategoryDTO dto);

    CategoryDTO update(Long id, CategoryDTO dto);

    void delete(Long id);

    CategoryDTO getById(Long id);

    List<CategoryDTO> getAll();

    // ✅ Đổi tên cho rõ ràng
    List<CategoryDTO> getCategoryTree();
}
