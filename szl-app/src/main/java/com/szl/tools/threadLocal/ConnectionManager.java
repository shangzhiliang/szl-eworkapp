package com.szl.tools.threadLocal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	private static final ThreadLocal<Connection> localConnection = new ThreadLocal<Connection>(){
		protected Connection initialValue() {
			try {
				return DriverManager.getConnection("");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		};
	}; 
	
	public static Connection getConnection(){
		return localConnection.get();
	}
}
