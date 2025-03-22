package com.mystore.app.repositories;

import com.mystore.app.entity.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Integer> {

	List<Product> findByNameContainingIgnoreCase(String name);

	List<Product> findByCategory(String category);

	List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

	List<Product> findBystockQuantityBetween(Integer minstock, Integer maxstock);


}
