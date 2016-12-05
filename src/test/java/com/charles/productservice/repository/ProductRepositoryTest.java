package com.charles.productservice.repository;

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
import org.springframework.transaction.annotation.Transactional;
import com.charles.productservice.model.Product;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductRepositoryTest extends EntityRepositoryTest {

	@Autowired
	private ProductRepository productRepository;

	@Test
	public void testFindAll() {
		List<Product> products = productRepository.findAll();
		assertThat(products, hasSize(4));
	}

	@Test
	public void testFindById() {
		Product product = productRepository.findById(2L);
		assertThat(product, is(notNullValue()));
		assertThat(product, hasProperty("name", equalTo("P2")));
		assertThat(product, hasProperty("description", equalTo("Product2")));
		assertThat(product, hasProperty("parent", is(notNullValue())));
		assertThat(product.getParent(), hasProperty("name", is(equalTo("P1"))));
		assertThat(product, hasProperty("children", hasSize(1)));
	}

	@Test
	public void testSave() {
		Product product = new Product();
		product.setName("P5");
		product.setDescription("Product5");
		product = productRepository.save(product);
		assertThat(product, hasProperty("id", is(notNullValue())));
	}

	@Test
	public void testUpdate() {
		Product product1 = new Product();
		product1.setId(1L);
		final String newName = "P1_new";
		product1.setName(newName);
		final String newDescription = "Product1_new";
		product1.setDescription(newDescription);
		productRepository.update(product1);
		Product product2 = productRepository.findById(1L);
		assertThat(product2, hasProperty("name", is(equalTo(newName))));
		assertThat(product2, hasProperty("description", is(equalTo(newDescription))));
		assertThat(product2, hasProperty("parent", is(nullValue())));
	}

	@Test
	public void testDelete() {
		Product product = new Product();
		product.setId(1L);
		productRepository.delete(product);
		Product product2 = productRepository.findById(1L);
		assertThat(product2, is(nullValue()));
	}

	@Test
	public void testDeleteWithImagesReferences() {
		Product product1 = new Product();
		product1.setId(1L);
		productRepository.delete(product1);
		Product product2 = productRepository.findById(1L);
		assertThat(product2, is(nullValue()));
	}

	@Test
	public void testFindChildProducts() {
		List<Product> product = productRepository.findChildrenProducts(1L);
		assertThat(product, hasSize(2));
		assertThat(product.get(0), is(notNullValue()));
		assertThat(product.get(1), is(notNullValue()));
	}

	@Test
	public void testFindChildProductsEmpty() {
		List<Product> product = productRepository.findChildrenProducts(4L);
		assertThat(product, hasSize(0));
	}
}
