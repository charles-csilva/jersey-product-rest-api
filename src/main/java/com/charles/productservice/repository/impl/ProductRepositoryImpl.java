package com.charles.productservice.repository.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.charles.productservice.model.Product;
import com.charles.productservice.repository.ProductRepository;

@Repository
public class ProductRepositoryImpl implements ProductRepository{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Product save(Product product) {
		Long id = (Long) sessionFactory.getCurrentSession().save(product);
		product.setId(id);
		return product;
	}

	public Product findById(Long id) {
		return (Product) sessionFactory.getCurrentSession().get(Product.class, id);
	}

	public Boolean update(Product product) {
		sessionFactory.getCurrentSession().update(product);
		return true;
	}

	public Boolean delete(Product product) {
		sessionFactory.getCurrentSession().delete(product);
		return true;
	}

	public List<Product> findAll() {
		return sessionFactory.getCurrentSession().createCriteria(Product.class).list();
	}
	
	@Override
	public List<Product> findChildrenProducts(Long productId) {
		return sessionFactory.getCurrentSession()
				.createCriteria(Product.class)
			    .add( Restrictions.eq("parent.id", productId) )
			    .list();
	}

}
