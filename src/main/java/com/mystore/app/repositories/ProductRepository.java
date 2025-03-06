package com.mystore.app.repositories;

import com.mystore.app.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Integer> {

    // TODO

}
