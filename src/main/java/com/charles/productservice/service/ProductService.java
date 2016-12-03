package com.charles.productservice.service;

import java.util.List;

import com.charles.productservice.dto.ProductDTO;

public interface ProductService {

	ProductDTO save(ProductDTO product);
	
	ProductDTO findById(Long id);

	Boolean update(ProductDTO product);

	Boolean delete(ProductDTO product);

	public List<ProductDTO> findAll();
	
	public List<ProductDTO> findChildrenProducts(Long id);

}