package com.charles.productservice.dto.mapper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import java.util.Arrays;
import org.junit.Test;
import com.charles.productservice.dto.ImageDTO;
import com.charles.productservice.dto.ProductDTO;
import com.charles.productservice.model.Image;
import com.charles.productservice.model.Product;

public class ProductMapperTest {

	@Test
	public void testToDTONullObject() {
		ProductDTO d = ProductMapper.toDTO(null, true, true);
		assertThat(d, is(nullValue()));
	}

	@Test
	public void testToEntityNullObject() {
		Product d = ProductMapper.toEntity(null);
		assertThat(d, is(nullValue()));
	}
	
	@Test
	public void testToDTONoImagesNoChildren() {
		Product product = new Product();
		product.setId(1L);
		product.setDescription("description_example");
		product.setName("name_example");
		product.setChildren(Arrays.asList(product));
		product.setImages(Arrays.asList(new Image()));
		ProductDTO prodDTO = ProductMapper.toDTO(product, false, false);
		assertThat(prodDTO, hasProperty("id", is(equalTo(product.getId()))));
		assertThat(prodDTO, hasProperty("name", is(equalTo(product.getName()))));
		assertThat(prodDTO, hasProperty("description", is(equalTo(product.getDescription()))));
		assertThat(prodDTO, hasProperty("children", is(nullValue())));
		assertThat(prodDTO, hasProperty("images", is(nullValue())));
	}
	
	@Test
	public void testToDTONoImages() {
		Product product = new Product();
		product.setId(1L);
		product.setDescription("description_example");
		product.setName("name_example");
		product.setChildren(Arrays.asList(new Product()));
		product.setImages(Arrays.asList(new Image()));
		ProductDTO prodDTO = ProductMapper.toDTO(product, false, true);
		assertThat(prodDTO, hasProperty("id", is(equalTo(product.getId()))));
		assertThat(prodDTO, hasProperty("name", is(equalTo(product.getName()))));
		assertThat(prodDTO, hasProperty("description", is(equalTo(product.getDescription()))));
		assertThat(prodDTO, hasProperty("children", is(notNullValue())));
		assertThat(prodDTO, hasProperty("images", is(nullValue())));
	}
	
	@Test
	public void testToDTONoChildren() {
		Product product = new Product();
		product.setId(1L);
		product.setDescription("description_example");
		product.setName("name_example");
		product.setChildren(Arrays.asList(new Product()));
		product.setImages(Arrays.asList(new Image()));
		ProductDTO prodDTO = ProductMapper.toDTO(product, true, false);
		assertThat(prodDTO, hasProperty("id", is(equalTo(product.getId()))));
		assertThat(prodDTO, hasProperty("name", is(equalTo(product.getName()))));
		assertThat(prodDTO, hasProperty("description", is(equalTo(product.getDescription()))));
		assertThat(prodDTO, hasProperty("children", is(nullValue())));
		assertThat(prodDTO, hasProperty("images", is(notNullValue())));
	}

	@Test
	public void testToEntity() {
		ProductDTO prodDTO = new ProductDTO();
		prodDTO.setId(1L);
		prodDTO.setDescription("description_example");
		prodDTO.setName("name_example");
		prodDTO.setParent(new ProductDTO());
		prodDTO.setChildren(Arrays.asList(new ProductDTO()));
		prodDTO.setImages(Arrays.asList(new ImageDTO(), new ImageDTO()));
		Product prod = ProductMapper.toEntity(prodDTO);
		assertThat(prod, hasProperty("id", is(equalTo(prodDTO.getId()))));
		assertThat(prod, hasProperty("name", is(equalTo(prodDTO.getName()))));
		assertThat(prod, hasProperty("description", is(equalTo(prodDTO.getDescription()))));
		assertThat(prod, hasProperty("parent", is(notNullValue())));
		assertThat(prod, hasProperty("children", is(notNullValue())));
		assertThat(prod, hasProperty("children", hasSize(1)));
		assertThat(prod, hasProperty("images", is(notNullValue())));
		assertThat(prod, hasProperty("images", hasSize(2)));
	}
	
	@Test
	public void testToDTOImagesChild() {
		Product prod = new Product();
		prod.setId(1L);
		prod.setDescription("description_example");
		prod.setName("name_example");
		prod.setParent(new Product());
		prod.setChildren(Arrays.asList(prod));
		prod.setImages(Arrays.asList(new Image(), new Image()));
		ProductDTO prodDTO = ProductMapper.toDTO(prod, true, true);
		assertThat(prodDTO, hasProperty("id", is(equalTo(prod.getId()))));
		assertThat(prodDTO, hasProperty("name", is(equalTo(prod.getName()))));
		assertThat(prodDTO, hasProperty("description", is(equalTo(prod.getDescription()))));
		assertThat(prodDTO, hasProperty("children", is(notNullValue())));
		assertThat(prodDTO, hasProperty("children", hasSize(1)));
		assertThat(prodDTO, hasProperty("images", is(notNullValue())));
		assertThat(prodDTO, hasProperty("images", hasSize(2)));
	}
}
