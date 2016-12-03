package com.charles.productservice.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charles.productservice.model.Image;
import com.charles.productservice.repository.ImageRepository;
import com.charles.productservice.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService{
	
	@Autowired
	private ImageRepository imageRepository;

	@Override
	@Transactional
	public Image save(Image image) {
		return imageRepository.save(image);
	}

	@Override
	@Transactional
	public Image findById(Long id) {
		return imageRepository.findById(id);
	}

	@Override
	@Transactional
	public Boolean update(Image image) {
		return imageRepository.update(image);
	}

	@Override
	@Transactional
	public Boolean delete(Image image) {
		return imageRepository.delete(image);
	}

	@Override
	@Transactional
	public List<Image> findAll() {
		return imageRepository.findAll();
	}

	@Override
	@Transactional
	public List<Image> findByProduct(Long productId) {
		return imageRepository.findByProduct(productId);
	}

}
