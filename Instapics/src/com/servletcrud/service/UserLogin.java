
package com.servletcrud.service;


import com.servletcrud.util.DBConnection;
import com.google.gson.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/userLogin")
public class UserLogin extends HttpServlet{
    DBConnection dBConnection;
   
    
    @Resource(name="jdbc/pic_uploader")
    private DataSource dataSource; 

   
  
  
    
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
	   Connection con=null;
	   PrintWriter out = response.getWriter();
	   try {
        response.setContentType("text/html;charset=UTF-8");
        
       
		try {
			con = dataSource.getConnection();
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
        String email = request.getParameter("name");
        String pass = request.getParameter("password");
        
        try {
            if(checkUser(con, email, pass))
            {
                RequestDispatcher rs = request.getRequestDispatcher("Welcome");
                rs.forward(request, response);
                String json = new Gson().toJson(response);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
                
                
                
            }
            else
            {
                out.println("Username or Password incorrect");
                RequestDispatcher rs = request.getRequestDispatcher("login.html");
                rs.include(request, response);
            }
        
	   }catch(Exception e) {
		   e.printStackTrace();
	   }
	   }finally {
		   if(con!=null) {
			   try {
				con.close();
				out.flush();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		   }
	   }
   }
   public boolean checkUser(Connection con, String name, String pass) throws SQLException{
	  PreparedStatement ps=null;
	  ResultSet rs=null;
	  boolean userCheck=false;
	   
	   try {
            ps =con.prepareStatement
                             ("select * from users where name=? and password=?");
         ps.setString(1, name);
         ps.setString(2, pass);
         rs =ps.executeQuery();
         userCheck=rs.next();
   }catch(SQLException e) {
	   e.printStackTrace();
   }finally {
	   rs.close();
	   ps.close();
	   
   }
	   return userCheck;
}
}
