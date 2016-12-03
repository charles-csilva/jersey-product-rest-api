package com.charles.productservice.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.charles.productservice.dto.ProductDTO;
import com.charles.productservice.dto.mapper.ProductMapper;
import com.charles.productservice.model.Product;
import com.charles.productservice.repository.ProductRepository;
import com.charles.productservice.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	@Transactional
	public ProductDTO save(ProductDTO product) {
		Product p = productRepository.save(ProductMapper.toEntity(product));
		return ProductMapper.toDTO(p);
	}

	@Override
	@Transactional
	public ProductDTO findById(Long id) {
		if (id == null){
			return null;
		}
		return ProductMapper.toDTO(productRepository.findById(id));
	}

	@Override
	@Transactional
	public Boolean update(ProductDTO order) {
		return productRepository.update(ProductMapper.toEntity(order));
	}

	@Override
	@Transactional
	public Boolean delete(ProductDTO order) {
		//		return productRepository.delete(order); //TODO fix
		return false;
	}

	@Override
	@Transactional
	public List<ProductDTO> findAll() {
		return productRepository.findAll().stream()
				.map(o -> ProductMapper.toDTO(o, false, true))
				.collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public List<ProductDTO> findChildrenProducts(Long productId) {
		return productRepository.findChildrenProducts(productId)
				.stream()
				.map(o -> ProductMapper.toDTO(o))
				.collect(Collectors.toList());
	}
}
