package com.charles.productservice.controller;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.charles.productservice.repository.RepositoryTestUtil;

@ContextConfiguration(locations = {"/testApplicationContext.xml"})
public class ControllerTest extends JerseyTest{
	
	@Autowired
	protected SessionFactory sessionFactory;
	
	@Before
	public void setup() throws Exception{
		RepositoryTestUtil.setupDatabase(sessionFactory);
	}
	
	@Override
    protected ResourceConfig configure() {
		
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);

        return new ResourceConfig(ProductController.class)
                .property("contextConfigLocation", "classpath:testApplicationContext.xml");
    }
}