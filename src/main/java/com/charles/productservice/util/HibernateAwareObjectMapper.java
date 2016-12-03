package com.charles.productservice.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

public class HibernateAwareObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = 8599827705438981770L;

	public HibernateAwareObjectMapper() {
        registerModule(new Hibernate4Module());
    }
}