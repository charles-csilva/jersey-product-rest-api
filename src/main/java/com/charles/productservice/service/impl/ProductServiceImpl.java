package com.charles.productservice.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
		return ProductMapper.toDTO(p, false, false);
	}
	
	@Override
	@Transactional
	public ProductDTO findById(Long id, boolean isGetImages, boolean isGetChildren) {
		
		if (id == null)
			return null;
		
		Product p = productRepository.findById(id);

		if(p == null)
			return null;
		
		return ProductMapper.toDTO(p, isGetImages, isGetChildren);
	}

	@Override
	@Transactional
	public Boolean update(ProductDTO product) {
		
		if(UpdatingImpliesCircularReference(product))
			return false;
		
		return productRepository.update(ProductMapper.toEntity(product));
	}

	@Override
	@Transactional
	public Boolean delete(ProductDTO product) {
		return productRepository.delete(ProductMapper.toEntity(product));
	}

	@Override
	@Transactional
	public List<ProductDTO> findAll(boolean isGetImages, boolean isGetChildren) {
		return productRepository.findAll().stream()
				.map(o -> ProductMapper.toDTO(o, isGetImages, isGetChildren))
				.collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public List<ProductDTO> findChildrenProducts(Long productId, boolean isGetImages, boolean isGetChildren) {
		return productRepository.findChildrenProducts(productId)
				.stream()
				.map(o -> ProductMapper.toDTO(o, isGetImages, isGetChildren))
				.collect(Collectors.toList());
	}
	
	private boolean UpdatingImpliesCircularReference(ProductDTO product)
	{
		ProductDTO currObject = product;
		ProductDTO currentParent = product.getParent() != null ? findById(product.getParent().getId(), false, false) : null;
		
		if(currentParent!= null && currObject.getId().equals(currentParent.getId()))
			return true;
		
		Set<Long> elementsKeys = new HashSet<Long>();
		int i = 0;
		while(i <= 3){
			
			if(currentParent == null)
				return false;
			
			if(elementsKeys.contains(currObject.getId()))
				return true;
			
			currObject = currentParent;
			currentParent = currObject.getParent() != null ? findById(product.getParent().getId(), false, false) : null;
			elementsKeys.add(currObject.getId());
			++i;
		}
		
		return true;
	}
}
