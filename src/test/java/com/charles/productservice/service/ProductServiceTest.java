package com.charles.productservice.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.charles.productservice.dto.ProductDTO;
import com.charles.productservice.repository.EntityRepositoryTest;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProductServiceTest extends EntityRepositoryTest {

	@Autowired
	private ProductService productService;

	@Test
	public void testFindAllNoImageNoChildren() {
		List<ProductDTO> products = productService.findAll(false, false);
		assertThat(products, hasSize(4));
		assertThat(products.get(0), hasProperty("children", is(nullValue())));
		assertThat(products.get(0), hasProperty("images", is(nullValue())));
	}

	@Test
	public void testFindAllOnlyChildren() {
		List<ProductDTO> products = productService.findAll(false, true);
		assertThat(products, hasSize(4));
		assertThat(products.get(0), hasProperty("images", is(nullValue())));
		assertThat(products.get(0), hasProperty("children", is(notNullValue())));
		assertThat(products.get(0), hasProperty("children", hasSize(2)));
	}

	@Test
	public void testFindAllOnlyImages() {
		List<ProductDTO> products = productService.findAll(true, false);
		assertThat(products, hasSize(4));
		assertThat(products.get(0), hasProperty("images", is(notNullValue())));
		assertThat(products.get(0), hasProperty("images", hasSize(2)));
		assertThat(products.get(0), hasProperty("children", is(nullValue())));
	}

	@Test
	public void testFindAllImagesAndChildren() {
		List<ProductDTO> products = productService.findAll(true, true);
		assertThat(products, hasSize(4));
		assertThat(products.get(0), hasProperty("images", is(notNullValue())));
		assertThat(products.get(0), hasProperty("images", hasSize(2)));
		assertThat(products.get(0), hasProperty("children", is(notNullValue())));
		assertThat(products.get(0), hasProperty("children", hasSize(2)));
	}

	@Test
	public void testFindByIdNoImageNoChildren() {
		ProductDTO productDTO = productService.findById(2L, false, false);
		assertThat(productDTO, is(notNullValue()));
		assertThat(productDTO, hasProperty("name", equalTo("P2")));
		assertThat(productDTO, hasProperty("description", equalTo("Product2")));
		assertThat(productDTO, hasProperty("parent", is(notNullValue())));
		assertThat(productDTO.getParent(), hasProperty("name", is(equalTo("P1"))));
		assertThat(productDTO.getParent(), hasProperty("name", is(equalTo("P1"))));
		assertThat(productDTO, hasProperty("children", is(nullValue())));
		assertThat(productDTO, hasProperty("images", is(nullValue())));
	}

	@Test
	public void testFindByIdOnlyChildren() {
		ProductDTO productDTO = productService.findById(2L, false, true);
		assertThat(productDTO, is(notNullValue()));
		assertThat(productDTO, hasProperty("name", equalTo("P2")));
		assertThat(productDTO, hasProperty("description", equalTo("Product2")));
		assertThat(productDTO, hasProperty("parent", is(notNullValue())));
		assertThat(productDTO.getParent(), hasProperty("name", is(equalTo("P1"))));
		assertThat(productDTO, hasProperty("images", is(nullValue())));
		assertThat(productDTO, hasProperty("children", is(notNullValue())));
		assertThat(productDTO, hasProperty("children", hasSize(1)));
	}

	@Test
	public void testFindByIdOnlyImages() {
		ProductDTO productDTO = productService.findById(2L, true, false);
		assertThat(productDTO, is(notNullValue()));
		assertThat(productDTO, hasProperty("name", equalTo("P2")));
		assertThat(productDTO, hasProperty("description", equalTo("Product2")));
		assertThat(productDTO, hasProperty("parent", is(notNullValue())));
		assertThat(productDTO.getParent(), hasProperty("name", is(equalTo("P1"))));
		assertThat(productDTO, hasProperty("images", is(notNullValue())));
		assertThat(productDTO, hasProperty("images", hasSize(1)));
		assertThat(productDTO, hasProperty("children", is(nullValue())));
	}

	@Test
	public void testFindByIdImageAndChildren() {
		ProductDTO productDTO = productService.findById(2L, true, true);
		assertThat(productDTO, is(notNullValue()));
		assertThat(productDTO, hasProperty("name", equalTo("P2")));
		assertThat(productDTO, hasProperty("description", equalTo("Product2")));
		assertThat(productDTO, hasProperty("parent", is(notNullValue())));
		assertThat(productDTO.getParent(), hasProperty("name", is(equalTo("P1"))));
		assertThat(productDTO, hasProperty("images", is(notNullValue())));
		assertThat(productDTO, hasProperty("images", hasSize(1)));
		assertThat(productDTO, hasProperty("children", is(notNullValue())));
		assertThat(productDTO, hasProperty("children", hasSize(1)));
	}

	@Test
	public void testFindByNullId() {
		ProductDTO productDTO = productService.findById(null, true, true);
		assertThat(productDTO, is(nullValue()));
	}

	@Test
	public void testSave() {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setName("P5");
		productDTO.setDescription("Product5");
		productDTO = productService.save(productDTO);
		assertThat(productDTO, hasProperty("id", is(notNullValue())));
	}

	@Test
	public void testUpdate() {
		ProductDTO productDTO1 = new ProductDTO();
		productDTO1.setId(1L);
		final String NEW_NAME = "P1_new";
		productDTO1.setName(NEW_NAME);
		final String NEW_DESCRIPTION = "Product1_new";
		productDTO1.setDescription(NEW_DESCRIPTION);
		boolean updated = productService.update(productDTO1);
		assertThat(updated, is(true));
		ProductDTO productDTO2 = productService.findById(1L, true, true);
		assertThat(productDTO2, hasProperty("name", is(equalTo(NEW_NAME))));
		assertThat(productDTO2, hasProperty("description", is(equalTo(NEW_DESCRIPTION))));
		assertThat(productDTO2, hasProperty("parent", is(nullValue())));
	}

	@Test
	public void testDelete() {
		ProductDTO productDTO1 = new ProductDTO();
		productDTO1.setId(4L);
		productService.delete(productDTO1);
		ProductDTO productDTO2 = productService.findById(4L, true, true);
		assertThat(productDTO2, is(nullValue()));
	}

	@Test
	public void testFindChildProducts() {
		List<ProductDTO> products = productService.findChildrenProducts(1L, true, true);
		assertThat(products, hasSize(2));
		assertThat(products.get(0), is(notNullValue()));
		assertThat(products.get(1), is(notNullValue()));
	}

	@Test
	public void testFindChildProductsEmpty() {
		List<ProductDTO> products = productService.findChildrenProducts(4L, true, true);
		assertThat(products, hasSize(0));
	}
}
