
package com.servletcrud.service;



import com.google.gson.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.servlet.AsyncContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/userLogin1")
public class UserLogin1 extends HttpServlet{
  
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

  
  
    
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
	    
        long startTime= System.nanoTime();
        PrintWriter out = response.getWriter();
      
       request.setAttribute( "org.apache.catalina.ASYNC_SUPPORTED", true );
           try {
		 
        	   
        	   int secs = Integer.valueOf("8000");
       		// max 10 seconds
       		if (secs > 10000)
       			secs = 10000;
		  final AsyncContext acontext = request.startAsync();
		  acontext.addListener(new AppAsyncListener());
		 acontext.setTimeout(9000);

		  
		  
	      acontext.start(new Runnable() {
	         public void run() {
	            String name = acontext.getRequest().getParameter("name");
	            String password = acontext.getRequest().getParameter("password");
	            try {
					if(checkUser(name, password, response)) {
						RequestDispatcher	rs = request.getRequestDispatcher("Upload.html");
						 
						System.out.println("request successful");
						 
						  } 
					else {
						out.println("Username or Password incorrect"); 
						
					    }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
	            
	          
	            acontext.complete();
	         
		      
		  
	         }
          
	      });
	      
	     System.out.println("time elapsed is:::" +(System.nanoTime()-startTime));
           } catch(Exception e) {
        	  e.printStackTrace();
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
