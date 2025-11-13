package com.example.demo_store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
    private Long parentId; // ✅ thêm dòng này
    private List<Long> productIds; // Optional
    private List<CategoryDTO> subCategories; // chỉ giữ danh sách con

}
