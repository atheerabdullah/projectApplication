package com.example.fproject.Repository;

import com.example.fproject.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository <Product , Integer> {

    Product findProductById(int id);
}
