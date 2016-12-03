package com.charles.productservice.dto.mapper;

import com.charles.productservice.dto.ImageDTO;
import com.charles.productservice.model.Image;

public class ImageMapper {
	
	
	public static ImageDTO toDTO(Image image){
		return ImageMapper.toDTO(image, false);
	}
	
	public static ImageDTO toDTO(Image image, boolean product){
		ImageDTO imageDTO = new ImageDTO();
		imageDTO.setId(image.getId());
		imageDTO.setType(image.getType());
		if (image.getProduct() != null && product){
			imageDTO.setProductFrom(ProductMapper.toDTO(image.getProduct()));
		}
		return imageDTO;
	}
	
	public static Image toEntity(ImageDTO imageDTO){
		Image image = new Image();
		image.setId(imageDTO.getId());
		image.setType(imageDTO.getType());
		if (imageDTO.getProductFrom() != null){
			image.setProduct(ProductMapper.toEntity(imageDTO.getProductFrom()));
		}
		return image;
	}
}
