package com.example.fproject.Service;


import com.example.fproject.ApiException.ApiException;
import com.example.fproject.Model.Product;
import com.example.fproject.Repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    public final ProductRepository productRepository;

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public void addProduct(Product product ) {
        productRepository.save(product);
    }

    public Product updateProduct(Integer id, Product product) {
        Product updateProduct = productRepository.findProductById(id);
        if (updateProduct == null) {
            throw new ApiException("Product not found with id " + id);
        }
        if (updateProduct.getName() != null) {
            updateProduct.setName(updateProduct.getName());
        }
        if (updateProduct.getPrice() != null) {
            updateProduct.setPrice(updateProduct.getPrice());
        }
        try {
            Product savedProduct = productRepository.save(updateProduct);
            if (savedProduct == null) {
                throw new RuntimeException("Failed to update Product with id: " + id);
            }
            return savedProduct;
        } catch (ConstraintViolationException ex) {
            throw new ValidationException("Validation failed", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to update movie with id: " + id, ex);
        }
    }


    public void deleteProduct(Integer id) {
        Product product  = productRepository.findProductById(id);
        if (product == null) {

            throw new EntityNotFoundException("Product not found with id " + id);
        }
        productRepository.delete(product);
    }

}
