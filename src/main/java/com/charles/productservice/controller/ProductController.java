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
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.charles.productservice.model.Product;
import com.charles.productservice.service.ProductService;
 
@Path("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Path("{productId}/images")
    public ImageController images(@PathParam("productId") long productId, @Context ResourceContext rc) {
		Product p = productService.findById(productId);
		if (p == null){
			throw new NotFoundException();
		}
        return rc.initResource(new ImageController(p));
    }
	
	/*
	 * Returns all the products
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> findAll() {
		return productService.findAll();
	}
	
	/*
	 * Returns all the child products of a product
	 */
	@GET
	@Path("/{id}/children")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> findAllChildren(@PathParam("id") Long id) {
		return productService.findChildrenProducts(id);
	}
	
	/*
	 * Returns a product by the id
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(@PathParam("id") Long id) {
		Product o = productService.findById(id);
        if(o != null) {
			return Response.status(HttpStatus.OK.value()).entity(o).build(); 
		} else {
			return Response.status(HttpStatus.NOT_FOUND.value()).build();
		}
	}

	/*
	 * Save a product, if the product is invalid is returned the status NOT_FOUND
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(Product product) {
		Product o = productService.save(product);
		if (o == null){
			return Response.status(HttpStatus.NOT_FOUND.value()).build();
		}
		return Response.status(HttpStatus.OK.value()).entity(o).build(); 
	}
	
	/*
	 * Update a product
	 */
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") Long id, Product product) {
		product.setId(id);
		if (productService.findById(product.getId()) != null) {
			if (productService.update(product)){
				return Response.status(HttpStatus.OK.value()).build();
			}
			
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
		Product p = productService.findById(id);
		if (p != null) {
			if (productService.delete(p)){
				return Response.status(HttpStatus.NO_CONTENT.value()).build();
			}
			
		}
		return Response.status(HttpStatus.NOT_FOUND.value()).build();
	}
}