// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DBConn.java

package database;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.sql.*;
import java.util.Properties;

public class DBConn
{

    public DBConn()
    {
    }

    public static Connection getConnection()
        throws Exception
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
		         
		}
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("in conexp");
        }
        System.out.println("get con");
        return con;
    }

   
}
