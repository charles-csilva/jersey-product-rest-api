package com.charles.productservice.service;

import java.util.List;

import com.charles.productservice.model.Image;

public interface ImageService {

	Image save(Image image);
	
	Image findById(Long id);

	Boolean update(Image image);

	Boolean delete(Image image);

	public List<Image> findAll();
	
	public List<Image> findByProduct(Long productId);

}