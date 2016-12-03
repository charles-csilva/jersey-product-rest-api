package com.charles.productservice.service;

import java.util.List;

import com.charles.productservice.model.Product;

public interface ProductService {

	Product save(Product product);
	
	Product findById(Long id);

	Boolean update(Product product);

	Boolean delete(Product product);

	public List<Product> findAll();
	
	public List<Product> findChildrenProducts(Long id);

}