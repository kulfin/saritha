

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
public class UserLogin extends HttpServlet {
	private static final BlockingQueue queue = new ArrayBlockingQueue<>(20000);
	Connection con = null;

	private InstrideJndi jndi = null;
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		jndi = new InstrideJndi("UserLogin");
	}

	public void destroy() {
		jndi.closeConnection();
	}

	private static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
       
		PrintWriter out = response.getWriter();
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
						   
							
							  ps =con.prepareStatement("select * from users u  where u.name=? and u.password=?");
							  ps.setString(1, name);
							  ps.setString(2, password);
					         rs =ps.executeQuery();
					         userCheck=rs.next();
					         
					         RequestDispatcher rD = request.getRequestDispatcher("Upload.html");
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
