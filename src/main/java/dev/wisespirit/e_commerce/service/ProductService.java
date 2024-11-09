package dev.wisespirit.e_commerce.service;

import dev.wisespirit.e_commerce.model.Product;
import dev.wisespirit.e_commerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getById(int id) {
        return productRepository.findById(Long.valueOf(id));
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
}
