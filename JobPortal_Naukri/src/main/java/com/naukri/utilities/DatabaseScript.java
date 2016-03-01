package com.naukri.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseScript {
	
	private String driverName = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://50.115.123.196:3306/";
	private String databaseName = "vitreos_cms_demo";
	private String userName = "psci";
	private String password = "psci";
	private Connection conn = null;
	
	
	public void retrieveData() throws Exception{
		
		try{
			Class.forName(driverName);
			conn = DriverManager.getConnection(url+databaseName, userName, password );
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery("Select * from dimensions_list");
			
			while(rs.next()){
				System.out.println(rs.getString(1));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(conn != null){
				conn = null;
				conn.close();
			}
		}
	 }
  }
