package com.mystore.app.service;

import com.mystore.app.entity.Product;
import com.mystore.app.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private Integer currentId = 1;

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {
        product.setId(currentId++);
        productRepository.save(product);
        return product;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(Integer id) {
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional.get();
    }

    public Product updateProduct(Integer id, Product product) {
        Product p = productRepository.findById(id).get();
        if (p == null) return null;
        p.setName(product.getName());
        p.setPrice(product.getPrice());
        p.setCategory(product.getCategory());
        p.setStockQuantity(product.getStockQuantity());
        productRepository.save(p);
        return p;
    }

    public String deleteProduct(Integer id) {
        Product p = productRepository.findById(id).get();
        if (p == null) return "Product Not Found";
        productRepository.delete(p);
        return "Product Deleted Successfully";
    }

    // TODO: Method to search products by name
    
    public List<Product> searchProductsByName(String name){
    	
    	List<Product> list=null;
    	list = productRepository.findByNameContainingIgnoreCase(name);
    	return list;
    }

	


    // TODO: Method to filter products by category
    
    public List<Product> filterProductByCategory(String category) {
    	List<Product> list =productRepository.findByCategory(category);
		// TODO Auto-generated method stub
		return list;
	}

	
	


    // TODO: Method to filter products by price range
    
    public List<Product> filterByPrice(Double minPrice, Double maxPrice) {
		// TODO Auto-generated method stub
    	List<Product> list = productRepository.findByPriceBetween(minPrice,maxPrice);
		return list;
	}

	



    // TODO: Method to filter products by stock quantity range

    public List<Product> filterByStock(Integer minStock, Integer maxStock) {
		// TODO Auto-generated method stub
		List<Product> list =productRepository.findBystockQuantityBetween(minStock,maxStock);
		return list;
	}

	public Page<Product> getPaginatedAndSortedProducts(int page, int pageSize, String sortBy, String sortDir) {
		  // Create a Pageable object with sorting and pagination
        Sort sort = Sort.by(Sort.Order.by(sortBy).with(Sort.Direction.fromString(sortDir)));
        PageRequest pageRequest = PageRequest.of(page, pageSize, sort);

        return productRepository.findAll(pageRequest);
	}
}
