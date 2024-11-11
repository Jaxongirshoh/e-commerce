package dev.wisespirit.e_commerce.controller;

import dev.wisespirit.e_commerce.model.Product;
import dev.wisespirit.e_commerce.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id){
        if (productService.getById(id).get()!=null){
            return new ResponseEntity<>(productService.getById(id).get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/product")
    public ResponseEntity<?> addproduct(@RequestPart Product product,
                                @RequestPart MultipartFile imageFile){
         try {
             Product product1 = productService.addProduct(product,imageFile);
             return new ResponseEntity<>(product1,HttpStatus.CREATED);
         }catch (Exception e){
             return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
         }

    }

    @GetMapping("/product/{id}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable int id){
        if (!productService.getById(id).isEmpty()){
            Product product = productService.getById(id).get();
            byte[] imageData = product.getImageData();
          //  return new ResponseEntity<>(imageData, HttpStatus.OK);
            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf(product.getImageType()))
                    .body(imageData);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id,
                                           @RequestPart Product product,
                                           @RequestPart MultipartFile imageFile) throws IOException {
        Product product1 = productService.updateProduct(id,product,imageFile);
        if (product1!=null){
            return new ResponseEntity<>(product1,HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deletProduct(@PathVariable int id){
        Product product = productService.getById(id).get();
        if (product==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword){
        List<Product> products = productService.searchProducts(keyword);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}

//29
