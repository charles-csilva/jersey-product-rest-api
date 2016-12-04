package com.charles.productservice.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.charles.productservice.dto.ImageDTO;
import com.charles.productservice.model.Image;

@RunWith(SpringJUnit4ClassRunner.class)
public class ImageControllerTest extends ControllerTest{
    
	@Test
    public void testGetById() throws Exception{
    	Response r = target("/products/1/images/1").request().get();

    	assertThat(r.getStatus(), is(HttpStatus.OK.value()));
    	
    	Image image = r.readEntity(Image.class);
		assertThat(image, is(notNullValue()));
		assertThat(image, hasProperty("type", is(equalTo("type1"))));
		assertThat(image, hasProperty("product", is(nullValue())));
    }
	
	@Test
    public void testGetByInexistentProduct() throws Exception{
    	Response r = target("/products/10/images").request().get();

    	assertThat(r.getStatus(), is(HttpStatus.NOT_FOUND.value()));
    }
    
    @Test
    public void testGetByInexistentId() throws Exception{
    	Response r = target("/products/1/images/4").request().get();

    	assertThat(r.getStatus(), is(HttpStatus.NOT_FOUND.value()));
    }
    
    
	@Test
	@SuppressWarnings("unchecked")
    public void testGetAllByProduct() throws Exception{
    	Response r = target("/products/1/images").request().get();

    	assertThat(r.getStatus(), is(HttpStatus.OK.value()));
    	List<Image> products = r.readEntity(new ArrayList<Image>().getClass());
		assertThat(products, hasSize(2));   
    }
	
	@Test 
	public void testSave(){
		ImageDTO p = new ImageDTO();
		p.setType("type_4");
		
		Response r = target("/products/1/images").request().post(Entity.entity(p, MediaType.APPLICATION_JSON_TYPE));
		
		assertThat(r.getStatus(), is(HttpStatus.CREATED.value()));
		
		Image image = r.readEntity(Image.class);
		assertThat(image, hasProperty("id", is(notNullValue())));
	}
	
	@Test
	public void testUpdate(){
		ImageDTO image = new ImageDTO();
		final String newType = "new_type1";
		
		image.setType(newType);
		
		Response r = target("/products/1/images/1").request()
				.put(Entity.entity(image, MediaType.APPLICATION_JSON_TYPE));
		assertThat(r.getStatus(), is(HttpStatus.OK.value()));
		
		Response r2 = target("/products/1/images/1").request().get();

    	assertThat(r2.getStatus(), is(HttpStatus.OK.value()));
    	
    	ImageDTO p2 = r2.readEntity(ImageDTO.class);
		assertThat(p2, is(notNullValue()));
		assertThat(p2, hasProperty("type", is(equalTo(newType))));
	}
	
	@Test
	public void testUpdateInexistentImage(){
		ImageDTO image = new ImageDTO();
		image.setType("type_new");
		
		Response r = target("/products/1/images/3").request().put(Entity.entity(image, MediaType.APPLICATION_JSON_TYPE));
		assertThat(r.getStatus(), is(HttpStatus.NOT_FOUND.value()));
	}
	
	@Test
	public void testDelete(){
		Response r1 = target("/products/1/images/1").request().get();
    	assertThat(r1.getStatus(), is(HttpStatus.OK.value()));
    	
		Response r2 = target("/products/1/images/1").request().delete();
		assertThat(r2.getStatus(), is(HttpStatus.OK.value()));
		
		Response r3 = target("/products/1/images/1").request().get();
    	assertThat(r3.getStatus(), is(HttpStatus.NOT_FOUND.value()));
	}
	
	@Test
	public void testDeleteInexistentImage(){
		Response r = target("/products/1/images/3").request().delete();
		assertThat(r.getStatus(), is(HttpStatus.NOT_FOUND.value()));
	}
}
