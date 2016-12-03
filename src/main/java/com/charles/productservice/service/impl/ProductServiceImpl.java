package com.charles.productservice.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charles.productservice.model.Product;
import com.charles.productservice.repository.ImageRepository;
import com.charles.productservice.repository.ProductRepository;
import com.charles.productservice.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ImageRepository imageRepository;

	@Override
	@Transactional
	public Product save(Product prd) {
		return productRepository.save(prd);
	}

	@Override
	@Transactional
	public Product findById(Long id) {
		return productRepository.findById(id);
	}

	@Override
	@Transactional
	public Boolean update(Product prd) {
		return productRepository.update(prd);
	}

	@Override
	@Transactional
	public Boolean delete(Product prd) {
		return productRepository.delete(prd);
	}

	@Override
	@Transactional
	public List<Product> findAll() {
		return productRepository.findAll();
	}
	
	@Override
	@Transactional
	public List<Product> findChildrenProducts(Long productId) {
		return productRepository.findChildrenProducts(productId);
	}
}
