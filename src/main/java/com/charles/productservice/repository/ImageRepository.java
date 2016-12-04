package com.charles.productservice.repository;

import java.util.List;

import com.charles.productservice.model.Image;

public interface ImageRepository {

	/*
	 * Save a image
	 */
	Image save(Image image);
	
	/*
	 * Find a image by the id
	 */
	Image findById(Long id);

	/*
	 * Update a image
	 */
	Boolean update(Image image);

	/*
	 * delete a image
	 */
	Boolean delete(Image image);

	/*
	 * List all images
	 */
	public List<Image> findAll();
	
	/*
	 * List all images of a product
	 */
	public List<Image> findByProductId(Long productId);
	
	Image findByIdAndProductId(Long id, Long productId);

}