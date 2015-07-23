package com.pylypchak.airfast.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;





public class ConnectionManager {
	private static ConnectionManager datasource;
    private static BasicDataSource ds;
	private ConnectionManager() {
		ds= new BasicDataSource();
		  ds.setDriverClassName("com.mysql.jdbc.Driver");
	        ds.setUsername("root");
	        ds.setPassword("");
	        ds.setUrl("jdbc:mysql://localhost/lowcost_company");
	      ds.setMaxWait(10000);
	        ds.setMaxIdle(100);
	        ds.setMaxActive(1000);
	        
	   
	}

	public static Connection getConnection() throws SQLException {
		if (datasource == null) {
			datasource = new ConnectionManager();
		}

		   return ds.getConnection();
		    
	}
}
