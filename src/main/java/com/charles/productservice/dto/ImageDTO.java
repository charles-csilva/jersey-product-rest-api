package com.charles.productservice.dto;

public class ImageDTO {
	
	private Long id;
	
	private String type;
	
	private ProductDTO productFrom;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ProductDTO getProductFrom() {
		return productFrom;
	}

	public void setProductFrom(ProductDTO product) {
		this.productFrom = product;
	}
}
