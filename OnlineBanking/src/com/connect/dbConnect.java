package com.connect;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
public class dbConnect
{
public static Connection getConnect()
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
}