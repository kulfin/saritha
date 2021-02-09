
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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

@WebServlet("/userLoginServlet")
public class UserLoginServlet extends HttpServlet {
	private static final BlockingQueue queue = new ArrayBlockingQueue<>(20000);
	Connection con = null;
 
	private InstrideJndi jndi = null;
	private static final long serialVersionUID = 1L;




	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		jndi = new InstrideJndi("UserLoginServlet");
	}

	public void destroy() {
		jndi.closeConnection();
	}

	private static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);

	
	@POST
	@Path("/history")
	   @javax.ws.rs.Produces(MediaType.TEXT_HTML)
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        System.out.println("coming inside post method");
		PrintWriter out = response.getWriter();
		 
	        response.setContentType("text/html");
	       
		 JSONArray jArray = new JSONArray();
		 JSONObject arrayObj = new JSONObject();
		
		HttpSession session=request.getSession();
		long startTime = System.nanoTime();
		try {

			final AsyncContext acontext = request.startAsync();
			executorService.execute(new Runnable() {
				public void run() {
					String name = acontext.getRequest().getParameter("name");
					String password = acontext.getRequest().getParameter("password");
					try {
						
						   PreparedStatement ps=null;
						     
							  ResultSet rs=null;
							  boolean userCheck=false;
							  long start = 0;
							
							DataSource ds=null;
							   start =  System.nanoTime(); 
									
									
						
						  
						      ds=jndi.getDataSource();
						      con=ds.getConnection();
						   
							
							  ps =con.prepareStatement("select * from login u  where  u.name=? and u.password=?");
							
							  ps.setString(1, name);
							  ps.setString(2, password);
					         rs =ps.executeQuery();
					         Integer id=0;
					         if(rs.next()) {
					        	id=rs.getInt(1);
					        	

						         arrayObj.put("id",id);
						         arrayObj.put("name",name);
					         }
					       

					       
					         
					         if(id!= null) {
					        session.setAttribute("id",id);
					         out.println("value of id stored in session is:::" +id);
					         }
					         Integer intLoginId = (Integer) session.getAttribute("id");
					         
					         String loginString = arrayObj.toString();
					        

					         session.setAttribute("arrayObj", loginString); 

                                     
					                
					         
					                
					                  
									  RequestDispatcher rD = request.getRequestDispatcher("../Upload.jsp");
									  rD.forward(request, response);
								  
									
								       								         
						
					         acontext.complete();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 

					

				}
				

			});

			System.out.println("Time taken for 3 threads to execute in nanoseconds is:::" + (System.nanoTime() - startTime));
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if(jndi!=null) {
				jndi.closeConnection();
			}
		
         if(con!=null) {
        	 try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         }
         
         
		}
	}



}
