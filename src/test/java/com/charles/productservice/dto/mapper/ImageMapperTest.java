package com.charles.productservice.dto.mapper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import org.junit.Test;
import com.charles.productservice.dto.ImageDTO;
import com.charles.productservice.dto.ProductDTO;
import com.charles.productservice.model.Image;
import com.charles.productservice.model.Product;

public class ImageMapperTest {

	@Test
	public void testToDTONullObject() {
		ImageDTO image = ImageMapper.toDTO(null, true);
		assertThat(image, is(nullValue()));
	}

	@Test
	public void testToEntityNullObject() {
		Image image = ImageMapper.toEntity(null);
		assertThat(image, is(nullValue()));
	}
	
	@Test
	public void testToDTONoProduct() {
		Image image = new Image();
		image.setId(1L);
		image.setType("type_example");
		ImageDTO i = ImageMapper.toDTO(image, true);
		assertThat(i, hasProperty("id", is(equalTo(image.getId()))));
		assertThat(i, hasProperty("type", is(equalTo(image.getType()))));
		assertThat(i, hasProperty("product", is(nullValue())));
	}

	@Test
	public void testToEntity() {
		ImageDTO image = new ImageDTO();
		image.setId(1L);
		image.setProduct(new ProductDTO());
		image.setType("type_example");
		Image i = ImageMapper.toEntity(image);
		assertThat(i, hasProperty("id", is(equalTo(image.getId()))));
		assertThat(i, hasProperty("type", is(equalTo(image.getType()))));
		assertThat(i, hasProperty("product", is(notNullValue())));
	}
	
	@Test
	public void testToDTOProduct() {
		Image image = new Image();
		image.setId(1L);
		image.setProduct(new Product());
		image.setType("type_example");
		ImageDTO i = ImageMapper.toDTO(image, true);
		assertThat(i, hasProperty("id", is(equalTo(image.getId()))));
		assertThat(i, hasProperty("type", is(equalTo(image.getType()))));
		assertThat(i, hasProperty("product", is(notNullValue())));
	}
}
