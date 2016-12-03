package com.charles.productservice.dto;

import java.util.List;

public class ProductDTO {
	
	private Long id;
	
	private String name;
	
	private String description;
	
	private ProductDTO parentProduct;

	private List<ImageDTO> images;
	
	private List<ProductDTO> childrenProduct;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProductDTO getParentProduct() {
		return parentProduct;
	}

	public void setParent(ProductDTO parent) {
		this.parentProduct = parent;
	}
	
	public List<ImageDTO> getImages() {
		return images;
	}

	public void setImages(List<ImageDTO> images) {
		this.images = images;
	}

	public List<ProductDTO> getChildrenProduct() {
		return childrenProduct;
	}

	public void setChildren(List<ProductDTO> children) {
		this.childrenProduct = children;
	}
}

