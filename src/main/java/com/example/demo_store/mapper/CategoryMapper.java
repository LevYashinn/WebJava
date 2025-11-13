package com.example.demo_store.mapper;

import com.example.demo_store.dto.CategoryDTO;
import com.example.demo_store.entity.Product;
import com.example.demo_store.entity.Category;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    public CategoryDTO toDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());

        // Gắn parentId nếu có
        if (category.getParent() != null) {
            dto.setParentId(category.getParent().getId());
        }

        if (category.getProducts() != null) {
            dto.setProductIds(
                    category.getProducts().stream()
                            .map(Product::getId)
                            .collect(Collectors.toList()));
        }
        // Gắn danh mục con (chỉ lấy id + tên, tránh vòng lặp đệ quy)
        if (category.getSubCategories() != null && !category.getSubCategories().isEmpty()) {
            dto.setSubCategories(
                    category.getSubCategories().stream()
                            .map(sub -> {
                                CategoryDTO subDto = new CategoryDTO();
                                subDto.setId(sub.getId());
                                subDto.setName(sub.getName());
                                subDto.setParentId(category.getId());
                                return subDto;
                            })
                            .collect(Collectors.toList()));
        }
        return dto;
    }

    public Category toEntity(CategoryDTO dto) {
        if (dto == null)
            return null;

        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());

        // ⚠️ Không set parent hoặc subCategories ở đây,
        // vì quan hệ parent sẽ được xử lý trong Service (dựa vào parentId)
        // còn subCategories do JPA cascade tự quản lý

        return category;
    }
}