package com.charles.productservice.repository;

import java.util.List;

import com.charles.productservice.model.Product;

public interface ProductRepository {

	/*
	 * Save a product
	 */
	Product save(Product product);
	
	/*
	 * Find a product by the id
	 */
	Product findById(Long id);

	/*
	 * Update a product
	 */
	Boolean update(Product product);

	/*
	 * delete a product
	 */
	Boolean delete(Product product);

	/*
	 * List all products
	 */
	public List<Product> findAll();
	
	/*
	 * List all children products of a product
	 */
	public List<Product> findChildrenProducts(Long productId);

}