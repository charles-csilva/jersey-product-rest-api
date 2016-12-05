package com.charles.productservice.repository;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(locations = {"/TestApplicationContext.xml"})
public class EntityRepositoryTest {

	@Autowired
	protected SessionFactory sessionFactory;
	
	@Before
	public void setUp() throws Exception{
		RepositoryTestUtil.setupDatabase(sessionFactory);
	}
}
