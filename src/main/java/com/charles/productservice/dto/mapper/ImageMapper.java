package com.charles.productservice.dto.mapper;

import com.charles.productservice.dto.ImageDTO;
import com.charles.productservice.model.Image;

public class ImageMapper {
	
	
/*	public static ImageDTO toDTO(Image image){
		return ImageMapper.toDTO(image, false);
	}*/
	
	public static ImageDTO toDTO(Image image, boolean isGetProduct){
		
		if(image == null)
			return null;
		
		ImageDTO imageDTO = new ImageDTO();
		imageDTO.setId(image.getId());
		imageDTO.setType(image.getType());
		
		if (image.getProduct() != null && isGetProduct){
			imageDTO.setProduct(ProductMapper.toDTO(image.getProduct(), false, false));
		}
		
		return imageDTO;
	}
	
	public static Image toEntity(ImageDTO imageDTO){
		Image image = new Image();
		image.setId(imageDTO.getId());
		image.setType(imageDTO.getType());
		
		if (imageDTO.getProduct() != null){
			image.setProduct(ProductMapper.toEntity(imageDTO.getProduct()));
		}
		
		return image;
	}
}
