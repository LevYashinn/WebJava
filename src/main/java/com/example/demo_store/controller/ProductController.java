package com.example.demo_store.controller;

import com.example.demo_store.dto.ProductDTO;
import com.example.demo_store.entity.Product;
import com.example.demo_store.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000") // React frontend
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) {
        ProductDTO dto = productService.getProductById(id);

        // Chỉ thêm link self + all-products
        dto.add(linkTo(methodOn(ProductController.class).getProduct(id)).withSelfRel());
        dto.add(linkTo(methodOn(ProductController.class).getAllProducts()).withRel("all-products"));

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO dto) {
        return ResponseEntity.ok(productService.createProduct(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO dto) {
        return ResponseEntity.ok(productService.updateProduct(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    // Lấy tất cả sản phẩm hoặc search theo category + keyword
    @GetMapping("/search")
    public List<Product> getProducts(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String keyword) {
        return productService.searchProductsByCategoryAndKeyword(categoryId, keyword);
    }
}
