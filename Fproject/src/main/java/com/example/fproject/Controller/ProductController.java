package com.example.fproject.Controller;


import com.example.fproject.Model.Product;
import com.example.fproject.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {


        private final ProductService productService;

        @GetMapping("/getProduct")
        public ResponseEntity getAllProduct(){
            List<Product> products=productService.getAllProduct();
            return ResponseEntity.status(200).body(products);
        }

        @PostMapping("/addProduct")
        public ResponseEntity addProduct(@Valid @RequestBody Product product  ){
            productService.addProduct(product);
            return ResponseEntity.status(200).body("Product added");
        }

        @PutMapping("/updateProduct/{id}")
        public ResponseEntity<Object> updateProduct(@Valid @RequestBody Product product , @PathVariable Integer id){
            Product updatedMovie = productService.updateProduct(id, product);
            return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
        }

        @DeleteMapping("/deleteProduct/{id}")
        public ResponseEntity deleteProduct(@PathVariable Integer id){
            productService.deleteProduct(id);
            return ResponseEntity.status(200).body("the Product deleted");
        }
}
