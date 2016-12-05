package com.charles.productservice.repository;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.h2.H2DataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.SessionFactoryUtils;

/*
 * Utility class to configure DB Unit
 */
public class RepositoryTestUtil {

	public static void setupDatabase(SessionFactory sessionFactory) throws Exception {
		IDatabaseConnection conn = getConnection(sessionFactory);
		IDataSet dataSet = getDataSet();

		try {
			DatabaseOperation.CLEAN_INSERT.execute(conn, dataSet);
		} finally {
			conn.close();
		}
	}

	private static IDataSet getDataSet() throws DataSetException, IOException {
		InputStream inputStream = RepositoryTestUtil.class.getClassLoader().getResourceAsStream("DataSetTest.xml");
		FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
		builder.setColumnSensing(true);
		IDataSet dataset = builder.build(inputStream);
		return dataset;
	}

	private static IDatabaseConnection getConnection(SessionFactory sessionFactory)
			throws SQLException, DatabaseUnitException {
		Connection jdbcConn = SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
		IDatabaseConnection conn = new DatabaseConnection(jdbcConn);
		DatabaseConfig config = conn.getConfig();
		config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());
		return conn;
	}
}
