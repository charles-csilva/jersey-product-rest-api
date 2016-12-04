package com.charles.productservice.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ProductDTO {
	
	private Long id;
	
	private String name;
	
	private String description;
	
	@JsonIgnore
	private ProductDTO parentProduct;

	private List<ImageDTO> images;
	
	private List<ProductDTO> children;
	
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

	public List<ProductDTO> getChildren() {
		return children;
	}

	public void setChildren(List<ProductDTO> children) {
		this.children = children;
	}
}

