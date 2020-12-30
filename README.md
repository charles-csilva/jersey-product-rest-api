# Product REST API #

This project uses
- Java 8
- Maven
- H2 embedded
- Jersey
- Jackson
- Spring
- Hibernate
- JUnit/Hamcrest
- Spring Test
- DBUnit

# Execution #

## Via Docker ##
``` $ docker-compose up ```

## Via Maven ##
```$ mvn jetty:run```

# Usage #

### Products ###

``` GET localhost:8080/api/products ```\
Returns an array containing all products.

Query parameters

* isGetChildren (true/false) - includes products' children products.
* isGetImages (true/false) - includes product's images. 


``` GET localhost:8080/api/products/:id ```\
Returns the product identified by id.

Query parameters

* isGetChildren (true/false) - includes products' children products.
* isGetImages (true/false) - includes product's images. 


``` GET api/products/:id/children ```\
Returns the children products of the product identified by id.


``` GET api/products/:id/images ```\
Returns the image array of the product identified by id.


``` POST api/products ```\
Creates a new product.
```
{
    "name": "Product 1",
    "description": "Product 1 description",
    "parent": {"id": 1}
}
```
``` PUT api/products/:id ```\
Updates a existing product identified by the id.
```
{
    "name": "Product 1 ++",
    "description": "Product 1 description ++",
    "parent": {"id": 1}
}
```


``` DELETE api/products/:id ```\
Deletes a existing product identified by the id.


### Images ###

``` GET api/products/:productId/images ```\
Returns an array of all images of the product identified by productId.


``` GET api/products/:productId/images/:imageId ```\
Returns the image identified by productId and imageId.


``` POST api/products/:productId/images ```\
Creates a new image for the product identified by productId.
```
{
    "type": "image_type"
}
```

``` PUT api/products/:productId/images/:imageId ```\
Updates a existing image identified by productId and imageId.
```
{
    "type": "new_type"
}
```

``` DELETE api/products/:productId/images/:imageId ```\
Deletes an existing image identified by productId and imageId.