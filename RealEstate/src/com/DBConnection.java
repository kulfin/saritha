package com;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
public class DBConnection
{
	
	public static Connection getConnection()
	{
		
		Connection con=null;
	 
			try {
				// ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
				// Properties properties = new Properties();
				
				InputStream input=null;
				 

				
				
				 
				ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
				Properties properties=null;
				try {
					 properties = new Properties();
					properties.load(classLoader.getResourceAsStream("/config.properties"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
				// InputStream in = loader.getResourceAsStream("/config.properties");
				// properties.load(in);
				String driver=properties.getProperty("driverName");
				  String	url=properties.getProperty("url");
				String	userName=properties.getProperty("username");
				String	password1=properties.getProperty("password");
				  
			        Class.forName(driver);
			         con=DriverManager.getConnection(url,userName,password1);
			       // return con;
			
			 /*String baseurl=properties.getProperty("baseURL");
			 System.out.println("BASE URL  is::::" +baseurl);*/
			
		
	    }catch(Exception e)
	    {
	       e.printStackTrace();
	    }
	return con;
	    
	    

	}
	
	
	
	
	
/*	
  public static Connection getConnection()throws Exception
  {
     Class.forName("oracle.jdbc.driver.OracleDriver");
     return DriverManager.getConnection("jdbc:oracle:thin:@ipoghome:1521:xe","scott","tiger");
   }*/
}
