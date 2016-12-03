package com.charles.productservice.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;
import com.charles.productservice.dto.ImageDTO;
import com.charles.productservice.dto.ProductDTO;
import com.charles.productservice.model.Image;
import com.charles.productservice.model.Product;


public class ProductMapper {
	
	
	public static ProductDTO toDTO(Product product){
		return ProductMapper.toDTO(product, false, false);
	}
	
	public static ProductDTO toDTO(Product product, boolean images, boolean children){
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setDescription(product.getDescription());
		if (product.getParent() != null){
			productDTO.setParent(ProductMapper.toDTO(product.getParent()));
		}
		if (product.getImages() != null && images){
			productDTO.setImages((List<ImageDTO>) product.getImages().stream()
					.map(o -> ImageMapper.toDTO(o)).collect(Collectors.toList()));
		}
		if (product.getChildren() != null && children){
			productDTO.setChildren((List<ProductDTO>) product.getChildren().stream()
					.map(o -> ProductMapper.toDTO(o)).collect(Collectors.toList()));
		}
		return productDTO;
	}
	
	public static Product toEntity(ProductDTO productDTO){
		Product product = new Product();
		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		product.setDescription(productDTO.getDescription());
		if (productDTO.getParentProduct() != null){
			product.setParent(ProductMapper.toEntity(productDTO.getParentProduct()));
		}
		if (productDTO.getImages() != null){
			product.setImages((List<Image>) productDTO.getImages().stream()
					.map(o -> ImageMapper.toEntity(o)).collect(Collectors.toList()));
		}
		if (productDTO.getChildrenProduct() != null){
			product.setChildren((List<Product>) productDTO.getChildrenProduct().stream()
					.map(o -> ProductMapper.toEntity(o)).collect(Collectors.toList()));
		}
		return product;
	}
}
