package com.charles.productservice.service;

import java.util.List;

import com.charles.productservice.dto.ImageDTO;

public interface ImageService {

	ImageDTO save(ImageDTO image);
	
	ImageDTO findById(Long id);

	Boolean update(ImageDTO image);

	Boolean delete(ImageDTO image);

	public List<ImageDTO> findAll();
	
	public List<ImageDTO> findByProductId(Long productId);
	
	ImageDTO findByIdAndProductId(Long id, Long productId);

}