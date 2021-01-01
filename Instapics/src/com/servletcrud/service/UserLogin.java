
package com.servletcrud.service;



import com.google.gson.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.AsyncContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/userLogin")
public class UserLogin extends HttpServlet{
	 private static final BlockingQueue queue = new ArrayBlockingQueue<>(20000);
   Connection con=null;
   
   private InstrideJndi jndi = null;
   private static final long serialVersionUID = 1L;

   public void init(ServletConfig servletConfig) throws ServletException
   {
       super.init(servletConfig);
       jndi = new InstrideJndi("UserLogin");
   }

    
   public void destroy()
   {
       jndi.closeConnection();
   }

   private static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);


   
  
  
    
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
	     
       
        PrintWriter out = response.getWriter();
        long startTime = System.nanoTime();
           try {
		 
		  final AsyncContext acontext = request.startAsync();
	     executorService.execute(new Runnable() {
	         public void run() {
	            String name = acontext.getRequest().getParameter("name");
	            String password = acontext.getRequest().getParameter("password");
	            try {
					if(checkUser(name, password, response)) {
						
						RequestDispatcher	rs = request.getRequestDispatcher("Upload.html"); 
						rs.forward(request, response);
						 
						/*
						 * String json = new Gson().toJson(response);
						 * response.setContentType("application/json");
						 * response.setCharacterEncoding("UTF-8"); response.getWriter().write(json);
						 */
						    			  
						 
						  } 
					else {
						out.println("Username or Password incorrect"); 
						RequestDispatcher rs = request.getRequestDispatcher("login.html");
					    rs.include(request, response);
					    }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
	            
	            
	            acontext.complete();
		  
		     
	         }
          
	      });
		 
		  
		  System.out.println("Time taken for 10 threads to execute in nanoseconds is:::" +(System.nanoTime()-startTime));
           } catch(Exception e) {
        	  e.printStackTrace();
          }
		  
          finally { 
		
			} 
        	  }
          
		  
		  
          
          
		 
   
   public boolean checkUser(String name, String password, HttpServletResponse response) throws SQLException{
	   PreparedStatement ps=null;
	     
		  ResultSet rs=null;
		  boolean userCheck=false;
		  long start = 0;
		
		DataSource ds=null;
		   start =  System.nanoTime(); 
				
				try {
	
	  
	      ds=jndi.getDataSource();
	      con=ds.getConnection();
	   
		
		  ps =con.prepareStatement("select * from users u  where u.name=? and u.password=?");
		  ps.setString(1, name);
		  ps.setString(2, password);
         rs =ps.executeQuery();
         userCheck=rs.next();
	  
   }catch(Exception e) {
	   e.printStackTrace();
   }finally {
      if(con!=null) {
	  con.close();
      }
      if(ps!=null) {
	   ps.close();
      }
      if(rs!=null) {
	   rs.close();
      }
      if(jndi!=null) {
    	  jndi.closeConnection();
      }
   }
	  return userCheck;
}
	 
	 
   	 
}
