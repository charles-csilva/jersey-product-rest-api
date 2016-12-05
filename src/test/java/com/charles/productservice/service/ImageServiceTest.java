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
import com.charles.productservice.dto.ImageDTO;
import com.charles.productservice.repository.EntityRepositoryTest;

@RunWith(SpringJUnit4ClassRunner.class)
public class ImageServiceTest extends EntityRepositoryTest {

	@Autowired
	private ImageService imageService;

	@Autowired
	private ProductService productService;

	@Test
	public void testGetAll() {
		List<ImageDTO> images = imageService.findAll();
		assertThat(images, hasSize(3));
	}

	@Test
	public void testFindById() {
		ImageDTO imageDTO = imageService.findById(1L);
		assertThat(imageDTO, is(notNullValue()));
		assertThat(imageDTO, hasProperty("type", is(equalTo("type1"))));
		assertThat(imageDTO, hasProperty("product", is(nullValue())));
	}

	@Test
	public void testFindByProductId() {
		List<ImageDTO> images = imageService.findByProductId(1L);
		assertThat(images, hasSize(2));
		assertThat(images.get(0), is(notNullValue()));
		assertThat(images.get(1), is(notNullValue()));
	}

	@Test
	public void testFindByIdAndProductId() {
		ImageDTO imageDTO = imageService.findByIdAndProductId(3L, 2L);
		assertThat(imageDTO, is(notNullValue()));
		assertThat(imageDTO, hasProperty("id", is(equalTo(3L))));
		assertThat(imageDTO, hasProperty("type", is(equalTo("type3"))));
	}

	@Test
	public void testFindByIdAndProductIdInexistent() {
		ImageDTO imageDTO = imageService.findByIdAndProductId(1L, 3L);
		assertThat(imageDTO, is(nullValue()));
	}

	@Test
	public void testFindByNullId() {
		ImageDTO imageDTO = imageService.findById(null);
		assertThat(imageDTO, is(nullValue()));
	}

	@Test
	public void testSave() {
		ImageDTO imageDTO = new ImageDTO();
		imageDTO.setType("type4");
		imageDTO.setProduct(productService.findById(1L, false, false));
		imageDTO = imageService.save(imageDTO);
		assertThat(imageDTO, hasProperty("id", is(notNullValue())));
	}

	@Test
	public void testUpdate() {
		final String NEW_TYPE = "new_type";
		ImageDTO imageDTO1 = new ImageDTO();
		imageDTO1.setId(1L);
		imageDTO1.setType(NEW_TYPE);
		imageDTO1.setProduct(productService.findById(2L, false, false));
		imageService.update(imageDTO1);
		ImageDTO imageDTO2 = imageService.findById(1L);
		assertThat(imageDTO2, hasProperty("type", is(equalTo(NEW_TYPE))));
	}

	@Test
	public void testDelete() {
		ImageDTO imageDTO1 = new ImageDTO();
		imageDTO1.setId(3L);
		imageService.delete(imageDTO1);
		ImageDTO imageDTO2 = imageService.findById(3L);
		assertThat(imageDTO2, is(nullValue()));
	}
}
