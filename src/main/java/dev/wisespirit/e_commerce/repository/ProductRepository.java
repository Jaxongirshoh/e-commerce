package dev.wisespirit.e_commerce.repository;

import dev.wisespirit.e_commerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}