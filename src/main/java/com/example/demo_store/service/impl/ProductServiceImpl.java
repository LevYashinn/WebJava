package com.example.demo_store.service.impl;

import com.example.demo_store.dto.ProductDTO;
import com.example.demo_store.entity.Category;
import com.example.demo_store.entity.Product;
import com.example.demo_store.exception.ResourceNotFoundException;
import com.example.demo_store.mapper.ProductMapper;
import com.example.demo_store.repository.CategoryRepository;
import com.example.demo_store.repository.ProductRepository;
import com.example.demo_store.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository,
            ProductMapper productMapper,
            CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductDTO createProduct(ProductDTO dto) {
        Product product = productMapper.toEntity(dto, categoryRepository);
        return productMapper.toDTO(productRepository.save(product));
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        product.setNameProduct(dto.getNameProduct());
        product.setDescriptionProduct(dto.getDescriptionProduct());
        product.setPriceProduct(dto.getPriceProduct());
        product.setBrand(dto.getBrand());
        product.setQuantity(dto.getQuantity());
        product.setImageUrl(dto.getImageUrl());

        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            product.setCategory(category);
        } else {
            product.setCategory(null);
        }

        return productMapper.toDTO(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sản phẩm với ID = " + id));
        return productMapper.toDTO(product);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> getProductsByCategory(Long categoryId) {
        if (categoryId == null)
            return productRepository.findAll();
        return productRepository.findByCategory_Id(categoryId);
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        if (keyword == null || keyword.isEmpty())
            return productRepository.findAll();
        return productRepository.findByNameProductContainingIgnoreCase(keyword);
    }

    @Override
    public List<Product> searchProductsByCategoryAndKeyword(Long categoryId, String keyword) {
        if ((categoryId == null || categoryId == 0) && (keyword == null || keyword.isEmpty())) {
            return productRepository.findAll();
        } else if (categoryId == null || categoryId == 0) {
            return searchProducts(keyword);
        } else if (keyword == null || keyword.isEmpty()) {
            return getProductsByCategory(categoryId);
        } else {
            return productRepository.findByCategory_IdAndNameProductContainingIgnoreCase(categoryId, keyword);
        }
    }
}
