package com.charles.productservice.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;
import com.charles.productservice.dto.ImageDTO;
import com.charles.productservice.dto.ProductDTO;
import com.charles.productservice.model.Image;
import com.charles.productservice.model.Product;


public class ProductMapper {
	
	
/*	public static ProductDTO toDTO(Product product){
		return ProductMapper.toDTO(product, false, false, false);
	}*/
	
	public static ProductDTO toDTO(Product product, boolean isGetImages, boolean isGetChildren){
		
		if(product == null)
			return null;
		
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setDescription(product.getDescription());
		
		if (product.getImages() != null && isGetImages){
			productDTO.setImages((List<ImageDTO>) product.getImages().stream()
					.map(o -> ImageMapper.toDTO(o, false)).collect(Collectors.toList()));
		}
		
		if (product.getChildren() != null && isGetChildren){
			productDTO.setChildren((List<ProductDTO>) product.getChildren().stream()
					.map(o -> ProductMapper.toDTO(o, isGetImages, isGetChildren))
					.collect(Collectors.toList()));
		}

		return productDTO;
	}
	
	public static Product toEntity(ProductDTO productDTO){
		
		Product product = new Product();
		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		product.setDescription(productDTO.getDescription());
		
		if (productDTO.getParentProduct() != null)
			product.setParent(ProductMapper.toEntity(productDTO.getParentProduct()));

		if (productDTO.getImages() != null){
			product.setImages((List<Image>) productDTO.getImages().stream()
					.map(o -> ImageMapper.toEntity(o)).collect(Collectors.toList()));
		}

		return product;
	}
}
