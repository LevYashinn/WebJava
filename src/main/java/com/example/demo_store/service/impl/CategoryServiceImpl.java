package com.example.demo_store.service.impl;

import com.example.demo_store.dto.CategoryDTO;
import com.example.demo_store.entity.Category;
import com.example.demo_store.entity.Product;
import com.example.demo_store.mapper.CategoryMapper;
import com.example.demo_store.repository.CategoryRepository;
import com.example.demo_store.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository repo, CategoryMapper mapper) {
        this.categoryRepository = repo;
        this.categoryMapper = mapper;
    }

    @Override
    public CategoryDTO create(CategoryDTO dto) {
        Category category = categoryMapper.toEntity(dto);

        if (dto.getParentId() != null) {
            Category parent = categoryRepository.findById(dto.getParentId())
                    .orElseThrow(() -> new RuntimeException("Parent category not found"));
            category.setParent(parent);
        }

        return categoryMapper.toDTO(categoryRepository.save(category));
    }

    @Override
    public CategoryDTO update(Long id, CategoryDTO dto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        category.setName(dto.getName());

        // ✅ Gán lại parent nếu có parentId mới
        if (dto.getParentId() != null) {
            Category parent = categoryRepository.findById(dto.getParentId())
                    .orElseThrow(() -> new RuntimeException("Parent category not found"));
            category.setParent(parent);
        } else {
            category.setParent(null);
        }

        Category updated = categoryRepository.save(category);
        return categoryMapper.toDTO(updated);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryDTO getById(Long id) {
        return categoryMapper.toDTO(categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found")));
    }

    @Override
    public List<CategoryDTO> getAll() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    // ✅ Trả về cây danh mục (cha–con)
    @Override
    public List<CategoryDTO> getCategoryTree() {
        List<Category> parents = categoryRepository.findByParentIsNull();
        return parents.stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
    }
}
