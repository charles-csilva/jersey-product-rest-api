# README #

#### This project uses ####
- JDK 8
- Maven 3
- H2 embedded
- JAX-RS
- Jersey
- Jackson
- Spring
- Hibernate
- JUnit/Hamcrest
- Spring Test
- DBUnit

#### Commands ####
- mvn compile
- mvn jetty:run
- mvn test

## API ##

### Products ###

GET api/products
Returns an array with all the products.
Query parameters
- isGetChildren (true/false) - return the array of children products.
- isGetImages (true/false) - return the product images array. 


GET api/products/:id
Returns the product specified by id.
Query parameters
- isGetChildren (true/false) - return the array of children products.
- isGetImages (true/false) - return the product images array. 


GET api/products/:id/children
Returns the children products of a product specified by id.


GET api/products/:id/images
Returns the images array of a product specified by id.


POST api/products
Create a new product.
```
#!json
{
    "name": "Product 1",
    "description": "Product 1 description",
    "parent": {"id": 1}

}
```


PUT api/products/:id
Update a existing product identified by the id.
```
#!json
{
    "name": "Product 1 ++",
    "description": "Product 1 description ++",
    "parent": {"id": 1}

}
```


DELETE api/products/:id
Delete a existing product identified by the id.


### Images ###

GET api/products/:productId/images
Returns an array with all the images of the product specified by productId.


GET api/products/:productId/images/:imageId
Returns the image identified by productId, specified by the imageId of the image.


POST api/products/:productId/images
Create a new image specified for the product identified by productId.
```
#!json
{
    "type": "image_type"
}
```


PUT api/products/:productId/images/:imageId
Update a existing image identified by the productId and imageId.
```
#!json
{
    "type": "new_type"

}
```

DELETE api/products/:productId/images/:imageId 
Delete a existing image identified by the productId and imageId.