package com.example.demo_store.service;

import com.example.demo_store.dto.ProductDTO;
import com.example.demo_store.entity.Product;

import java.util.List;

public interface ProductService {
    ProductDTO createProduct(ProductDTO dto);

    ProductDTO updateProduct(Long id, ProductDTO dto);

    void deleteProduct(Long id);

    ProductDTO getProductById(Long id);

    List<ProductDTO> getAllProducts();

    List<Product> getProductsByCategory(Long categoryId);

    List<Product> searchProducts(String keyword);

    List<Product> searchProductsByCategoryAndKeyword(Long categoryId, String keyword);
}