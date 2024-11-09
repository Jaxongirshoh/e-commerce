package dev.wisespirit.e_commerce.controller;

import dev.wisespirit.e_commerce.model.Product;
import dev.wisespirit.e_commerce.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/home")
    public String greet(){
        return "Hello from Spring Boot!";
    }

    @GetMapping("/products")
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable int id){
        return productService.getById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @PostMapping("/add_product")
    public Product addproduct(@RequestBody Product product){
        return productService.addProduct(product);
    }
}
