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
import com.charles.productservice.model.Image;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class ImageRepositoryTest extends EntityRepositoryTest{
	
	@Autowired
	private ImageRepository imageRepository;
	
	@Test
	public void testFindAll(){
		List<Image> images = imageRepository.findAll();
		assertThat(images, hasSize(3));
	}
	
	@Test
	public void testFindById(){
		Image image = imageRepository.findById(1L);
		assertThat(image, is(notNullValue()));
		assertThat(image, hasProperty("type", is(equalTo("type1"))));
		assertThat(image, hasProperty("product", is(notNullValue())));
	}
	
	@Test
	public void testFindByProductId(){
		List<Image> image = imageRepository.findByProductId(1L);
		assertThat(image, hasSize(2));
		assertThat(image.get(0), is(notNullValue()));
		assertThat(image.get(1), is(notNullValue()));
	}
	
	@Test
	public void testFindByIdAndProductId(){
		Image image = imageRepository.findByIdAndProductId(3L, 2L);
		assertThat(image, is(notNullValue()));
		assertThat(image, hasProperty("id", is(equalTo(3L))));
		assertThat(image, hasProperty("type", is(equalTo("type3"))));
	}
	
	@Test
	public void testFindByIdAndProductIdInexistent(){
		Image image = imageRepository.findByIdAndProductId(1L, 3L);
		assertThat(image, is(nullValue()));
	}
	
	@Test
	public void testSave(){
		Image image = new Image();
		image.setType("type4");
		image.setProduct(imageRepository.findById(1L).getProduct());
		image = imageRepository.save(image);
		assertThat(image, hasProperty("id", is(notNullValue())));
	}
	
	@Test
	public void testUpdate(){
		String newType = "new_type";
		Image image1 = new Image();
		image1.setId(1L);
		image1.setType(newType);
		imageRepository.update(image1);
		Image image2 = imageRepository.findById(1L);
		assertThat(image2, hasProperty("type", is(equalTo(newType))));
		assertThat(image2, hasProperty("product", is(nullValue())));
	}
	
	@Test
	public void testDelete(){
		Image image1 = new Image();
		image1.setId(3L);
		imageRepository.delete(image1);
		Image image2 = imageRepository.findById(3L);	
		assertThat(image2, is(nullValue()));
	}
}
