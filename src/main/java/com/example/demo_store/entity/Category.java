package com.example.demo_store.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // 🔹 Danh mục cha
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonBackReference // ngắt vòng lặp ở đây
    private Category parent;

    // 🔹 Danh mục con
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference // phần cha cho phép serialize subCategories
    private List<Category> subCategories;

    // 🔹 Sản phẩm trong danh mục
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @JsonIgnore // không cần thiết trong JSON trả ra
    private List<Product> products;
}
