package com.ef.jdbc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;

/**
 * @author Vitor Carrilho - 01/03/2018
 *
 */
public class ConnectionPool {

	private static GenericObjectPool gPool = null;
	
	private static DataSource dataSource;

	static {
		
		Properties properties = new Properties();
		try {
			properties.load(Files.newInputStream(Paths.get("application.properties")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String jdbcUrl = properties.getProperty("jdbc.bd.url");
		String jdbcUser = properties.getProperty("jdbc.user");
		String jdbcPassword = properties.getProperty("jdbc.password");
		
		// Creates an Instance of GenericObjectPool That Holds Our Pool of Connections Object!
		gPool = new GenericObjectPool();
		gPool.setMaxActive(50);

		// Creates a ConnectionFactory Object Which Will Be Use by the Pool to Create the Connection Object!
		ConnectionFactory cf = new DriverManagerConnectionFactory(jdbcUrl, jdbcUser, jdbcPassword);

		// Creates a PoolableConnectionFactory That Will Wraps the Connection Object Created by the ConnectionFactory to Add Object Pooling Functionality!
		PoolableConnectionFactory pcf = new PoolableConnectionFactory(cf, gPool, null, null, false, true);
		dataSource = new PoolingDataSource(gPool);
	}
	
	public static DataSource getDataSource() {
		return dataSource;
	}
}
