package com.charles.productservice.repository.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.charles.productservice.model.Image;
import com.charles.productservice.repository.ImageRepository;

@Repository
public class ImageRepositoryImpl implements ImageRepository{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Image save(Image image) {
		Long id = (Long) sessionFactory.getCurrentSession().save(image);
		image.setId(id);
		return image;
	}

	public Image findById(Long id) {
		return (Image) sessionFactory.getCurrentSession().get(Image.class, id);
	}

	public Boolean update(Image image) {
		sessionFactory.getCurrentSession().update(image);
		return true;
	}

	public Boolean delete(Image image) {
		sessionFactory.getCurrentSession().delete(image);
		return true;
	}

	public List<Image> findAll() {
		return sessionFactory.getCurrentSession().createCriteria(Image.class).list();
	}

	@Override
	public List<Image> findByProductId(Long productId) {
		return sessionFactory.getCurrentSession().createCriteria(Image.class)
			    .add( Restrictions.eq("product.id", productId) )
			    .list();
	}

}
