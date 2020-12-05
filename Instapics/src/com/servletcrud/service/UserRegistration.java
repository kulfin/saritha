/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servletcrud.service;

import com.google.gson.Gson;
import com.servletcrud.util.DBConnection;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


public class UserRegistration extends HttpServlet {




@Resource(name="jdbc/pic_uploader")
private DataSource dataSource; 

@Override
   public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
 PreparedStatement ps=null;
 
response.setContentType("text/html");  
PrintWriter out = response.getWriter();  
PrintWriter outWriter = response.getWriter();  
Connection con=null;
try {

try {
	con = dataSource.getConnection();
} catch (SQLException e) {
	
	e.printStackTrace();
}         
  
String name=request.getParameter("name");  
String pass=request.getParameter("password");  

 outWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(java.io.FileDescriptor.out), "UTF-8"), 512));
outWriter.println("Username: "+name+" Pass: "+pass);

outWriter.flush();


// System.out.println("Username: "+name+" Pass: "+pass);// above 3 lines 3 times faster than one in the comment here.
          

     
 ps=con.prepareStatement(  
"insert into users (name,password) values(?,?)");  
  
 
ps.setString(1,name);  
ps.setString(2,pass);  
 
          
int i=ps.executeUpdate();  
if(i>0)  
out.print("You are successfully registered...");  


      
          
}catch (Exception e2) { e2.printStackTrace();;}  
finally {
	if(con!=null) {
		try {
			con.close();
			ps.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		
	}
	
}
          
out.close();  
outWriter.close();
}  

}
