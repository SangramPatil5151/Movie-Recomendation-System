package org.MovieRecomendation.Config;
import java.io.*;
import java.sql.*;
import java.util.*;
public class DBConfig {
	 private static Connection conn;
	 private static PreparedStatement stmt;
	 private static ResultSet rs;
	 
	private static DBConfig db=null;
	private  DBConfig()
	 {
		 try
		 {
			
			 Properties p= new Properties();
			 p.load(PathHelper.fin);
			 String driverClassName=p.getProperty("driver.classname");
			 String username=p.getProperty("db.username");
			 String password=p.getProperty("db.password");
			 String url=p.getProperty("db.url");
			Class.forName(driverClassName); 
			conn=DriverManager.getConnection(url, username, password);
			if(conn!=null)
			{
				System.out.println("Database Conected....");
			}
			else
			{
				System.out.println("Database not Conected....");
			}
		 }
		 catch(Exception ex)
		 {
			 System.out.println("error is :"+ex);
		 }
	 }
	 public static DBConfig getDBIntance()
	 {
		 if(db==null)
		 {
			 db=new DBConfig();
		 }
		 return db;
	 }
	 public static Connection getConnection()
	 {
		 return conn;
	 }
	 public static PreparedStatement  getStatement()
	 {
		 return stmt;
	 }
	 public static ResultSet getResultSet()
	 {
		 return rs;
	 }
}
