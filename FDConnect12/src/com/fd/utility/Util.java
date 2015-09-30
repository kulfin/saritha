package com.fd.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Util 
{

	public static String driver;
//	public static String url = "jdbc:mysql://182.50.142.1:3306/fourth_dimension";
	public static String url;
//	public static String url = "jdbc:mysql://192.168.1.121:3307/fourth_dimension";

	public static String userName;
	public static String password;
	public static int initialConnections = 1;
	public static int maxConnections = 5;
	
	static
	{
		InputStream input=null;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Properties properties=null;
		try {
			 properties = new Properties();
			properties.load(classLoader.getResourceAsStream("/config.properties"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		 /*String baseurl=properties.getProperty("baseURL");
		 System.out.println("BASE URL  is::::" +baseurl);*/
		
		driver=properties.getProperty("driverName");
		url=properties.getProperty("url");
		userName=properties.getProperty("username");
		password=properties.getProperty("password");
	}
}
