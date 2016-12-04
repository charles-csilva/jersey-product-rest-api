package com.charles.productservice.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.charles.productservice.dto.ImageDTO;
import com.charles.productservice.dto.ProductDTO;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProductControllerTest extends ControllerTest{
    
    @Test
	public void testFindAllNoImageNoChild(){
    	Response r = target("/products").request().get();

    	assertThat(r.getStatus(), is(HttpStatus.OK.value()));
    	List<ProductDTO> products = r.readEntity(new GenericType<List<ProductDTO>>(){});
		assertThat(products, hasSize(4));
		assertThat(products.get(0), hasProperty("children", is(nullValue())));
		assertThat(products.get(0), hasProperty("images", is(nullValue())));
	}
	
	@Test
	public void testFindAllNoImageChild(){
		Response r = target("/products").queryParam("isGetChildren", "true").request().get();

    	assertThat(r.getStatus(), is(HttpStatus.OK.value()));
    	List<ProductDTO> products = r.readEntity(new GenericType<List<ProductDTO>>(){});
		assertThat(products, hasSize(4));
		assertThat(products.get(0), hasProperty("images", is(nullValue())));
		assertThat(products.get(0), hasProperty("children", is(notNullValue())));
		assertThat(products.get(0), hasProperty("children", hasSize(2)));
	}
	
	@Test
	public void testFindAllImageNoChild(){
		Response r = target("/products").queryParam("isGetImages", "true").request().get();

    	assertThat(r.getStatus(), is(HttpStatus.OK.value()));
    	List<ProductDTO> products = r.readEntity(new GenericType<List<ProductDTO>>(){});
		assertThat(products, hasSize(4));
		assertThat(products.get(0), hasProperty("images", is(notNullValue())));
		assertThat(products.get(0), hasProperty("images", hasSize(2)));
		assertThat(products.get(0), hasProperty("children", is(nullValue())));
	}
	
	@Test
	public void testFindAllImageChild(){
		Response r = target("/products").queryParam("isGetChildren", "true")
				.queryParam("isGetImages", "true").request().get();

    	assertThat(r.getStatus(), is(HttpStatus.OK.value()));
    	List<ProductDTO> products = r.readEntity(new GenericType<List<ProductDTO>>(){});
		assertThat(products, hasSize(4));
		assertThat(products.get(0), hasProperty("images", is(notNullValue())));
		assertThat(products.get(0), hasProperty("images", hasSize(2)));
		assertThat(products.get(0), hasProperty("children", is(notNullValue())));
		assertThat(products.get(0), hasProperty("children", hasSize(2)));
	}
    
    @Test
    public void testFindByIdNoImageNoChild() throws Exception{
    	Response r = target("/products/2").request().get();

    	assertThat(r.getStatus(), is(HttpStatus.OK.value()));
    	
		ProductDTO p = r.readEntity(ProductDTO.class);
		assertThat(p, is(notNullValue()));
		assertThat(p, hasProperty("name", equalTo("P2")));
		assertThat(p, hasProperty("description", equalTo("Product2")));

		assertThat(p, hasProperty("children", is(nullValue())));
		assertThat(p, hasProperty("images", is(nullValue())));
    }
    
    @Test
	public void testFindByIdNoImageChild(){
    	Response r = target("/products/2").queryParam("isGetChildren", "true").request().get();

    	assertThat(r.getStatus(), is(HttpStatus.OK.value()));
    	
		ProductDTO p = r.readEntity(ProductDTO.class);
		assertThat(p, is(notNullValue()));
		assertThat(p, hasProperty("name", equalTo("P2")));
		assertThat(p, hasProperty("description", equalTo("Product2")));

		assertThat(p, hasProperty("images", is(nullValue())));
		assertThat(p, hasProperty("children", is(notNullValue())));
		assertThat(p, hasProperty("children", hasSize(1)));
	}
	
	@Test
	public void testFindByIdImageNoChild(){
		Response r = target("/products/2").queryParam("isGetImages", "true").request().get();

    	assertThat(r.getStatus(), is(HttpStatus.OK.value()));
    	
		ProductDTO p = r.readEntity(ProductDTO.class);
		assertThat(p, is(notNullValue()));
		assertThat(p, hasProperty("name", equalTo("P2")));
		assertThat(p, hasProperty("description", equalTo("Product2")));
		assertThat(p, hasProperty("images", is(notNullValue())));
		assertThat(p, hasProperty("images", hasSize(1)));
		assertThat(p, hasProperty("children", is(nullValue())));
	}
	
	@Test
	public void testFindByIdImageChild(){
		Response r = target("/products/2").queryParam("isGetChildren", "true")
				.queryParam("isGetImages", "true").request().get();

    	assertThat(r.getStatus(), is(HttpStatus.OK.value()));
    	
		ProductDTO p = r.readEntity(ProductDTO.class);
		assertThat(p, is(notNullValue()));
		assertThat(p, hasProperty("name", equalTo("P2")));
		assertThat(p, hasProperty("description", equalTo("Product2")));

		assertThat(p, hasProperty("images", is(notNullValue())));
		assertThat(p, hasProperty("images", hasSize(1)));
		assertThat(p, hasProperty("children", is(notNullValue())));
		assertThat(p, hasProperty("children", hasSize(1)));
	}
    
    @Test
    public void testFindByInexistentId() throws Exception{
    	Response r = target("/products/999").request().get();

    	assertThat(r.getStatus(), is(HttpStatus.NOT_FOUND.value()));
    }
    
	@Test 
	public void testSave(){
		ProductDTO p = new ProductDTO();
		p.setName("P5");
		p.setDescription("Product5");
		ImageDTO i1 = new ImageDTO();
		i1.setId(1L);
		p.setImages(Arrays.asList(i1));
		
		Response r = target("/products").request().post(Entity.entity(p, MediaType.APPLICATION_JSON_TYPE));
		
		assertThat(r.getStatus(), is(HttpStatus.CREATED.value()));
		
		ProductDTO p2 = r.readEntity(ProductDTO.class);
		assertThat(p2, hasProperty("id", is(notNullValue())));
	}
	
	
	@Test
	public void testUpdate(){
		ProductDTO p1 = new ProductDTO();
		p1.setId(1L);
		p1.setName("P1_new");
		p1.setDescription("Product1_new");
		
		Response r = target("/products/1").request().put(Entity.entity(p1, MediaType.APPLICATION_JSON_TYPE));
		assertThat(r.getStatus(), is(HttpStatus.OK.value()));
		
		Response r2 = target("/products/1").request().get();

    	assertThat(r2.getStatus(), is(HttpStatus.OK.value()));
    	
		ProductDTO p2 = r2.readEntity(ProductDTO.class);
		assertThat(p2, is(notNullValue()));
		assertThat(p2, hasProperty("name", is(equalTo("P1_new"))));
		assertThat(p2, hasProperty("description", is(equalTo("Product1_new"))));
	}
	
	@Test
	public void testUpdateInexistentProduct(){
		ProductDTO p1 = new ProductDTO();
		p1.setName("P10_new");
		p1.setDescription("Product10_new");
		
		Response r = target("/products/999").request().put(Entity.entity(p1, MediaType.APPLICATION_JSON_TYPE));
		assertThat(r.getStatus(), is(HttpStatus.NOT_FOUND.value()));
	}
	
	@Test
	public void testDelete(){
		Response r1 = target("/products/4").request().get();
    	assertThat(r1.getStatus(), is(HttpStatus.OK.value()));
    	
		Response r2 = target("/products/4").request().delete();
		assertThat(r2.getStatus(), is(HttpStatus.OK.value()));
		
		Response r3 = target("/products/4").request().get();
    	assertThat(r3.getStatus(), is(HttpStatus.NOT_FOUND.value()));
	}
	
	@Test
	public void testDeleteInexistentImage(){
		Response r = target("/products/5").request().delete();
		assertThat(r.getStatus(), is(HttpStatus.NOT_FOUND.value()));
	}
	
	@Test
	public void testFindChildProducts(){
		Response r = target("/products/1/children").request().get();

    	assertThat(r.getStatus(), is(HttpStatus.OK.value()));
    	
    	List<ProductDTO> p = r.readEntity(new GenericType<List<ProductDTO>>(){});
		assertThat(p, hasSize(2));
	}
	
	@Test
	public void testFindChildProductsEmpty(){
		Response r = target("/products/4/children").request().get();

    	assertThat(r.getStatus(), is(HttpStatus.OK.value()));
		List<ProductDTO> p = r.readEntity(new GenericType<List<ProductDTO>>(){});
		assertThat(p, hasSize(0));
	}
}
