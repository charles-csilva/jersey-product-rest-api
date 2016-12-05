package com.charles.productservice.controller;
 
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.charles.productservice.dto.ProductDTO;
import com.charles.productservice.model.Product;
import com.charles.productservice.service.ProductService;
 
@Path("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Path("{productId}/images")
    public ImageController images(@PathParam("productId") long productId, @Context ResourceContext rc) {
		
		ProductDTO p = productService.findById(productId, false, false);

		if (p == null)
			throw new NotFoundException();

        return rc.initResource(new ImageController(p));
    }
	
	/*
	 * Returns all the products
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProductDTO> findAll(
			@QueryParam("isGetImages") boolean isGetImages
			, @QueryParam("isGetChildren") boolean isGetChildren) {
		return productService.findAll(isGetImages, isGetChildren);
	}
	
	/*
	 * Returns all the child products of a product
	 */
	@GET
	@Path("/{id}/children")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProductDTO> findAllChildren(@PathParam("id") Long id
			, @QueryParam("isGetImages") boolean isGetImages
			, @QueryParam("isGetChildren") boolean isGetChildren) {
		return productService.findChildrenProducts(id, isGetImages, isGetChildren);
	}
	
	/*
	 * Returns a product by the id
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(@PathParam("id") Long id
			, @QueryParam("isGetImages") boolean isGetImages
			, @QueryParam("isGetChildren") boolean isGetChildren) {
		
		ProductDTO o = productService.findById(id, isGetImages, isGetChildren);
        if(o != null)
			return Response.status(HttpStatus.OK.value()).entity(o).build();
        
		return Response.status(HttpStatus.NOT_FOUND.value()).build();
	}

	/*
	 * Save a product
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(ProductDTO product) {
		
		ProductDTO o = productService.save(product);
		
		if (o == null)
			return Response.status(HttpStatus.NOT_FOUND.value()).build();
		
		return Response.status(HttpStatus.CREATED.value()).entity(o).build(); 
	}
	
	/*
	 * Update a product
	 */
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") Long id, ProductDTO product) {
		
		product.setId(id);
		
		if (productService.findById(product.getId(), false, false) != null) {
			
			boolean success = false;
			//try{
				success = productService.update(product);
			//}catch (Exception e) { }
			
			if (success)
				return Response.status(HttpStatus.OK.value()).build();
			else return Response.status(HttpStatus.BAD_REQUEST.value()).build();
		}
		
		return Response.status(HttpStatus.NOT_FOUND.value()).build();
	}
	
	/*
	 * Delete a product
	 */
	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") Long id) {
		
		ProductDTO p = productService.findById(id, false, false);
		
		if (p != null) {
			
			boolean success = false;
			try{
				success = productService.delete(p);
			}catch (Exception e) { }
			
			if (success)
				return Response.status(HttpStatus.OK.value()).build();
			else return Response.status(HttpStatus.BAD_REQUEST.value()).build();
		}
		
		return Response.status(HttpStatus.NOT_FOUND.value()).build();
	}
}