package com.servletcrud.service;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.servlet.AsyncContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/usersView")
public class UsersView{
	
	private static final BlockingQueue queue = new ArrayBlockingQueue<>(20000);
	
	private InstrideJndi jndi = new InstrideJndi("UsersView");
	 Connection con = null;



	private static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);

	
	@POST
	@Produces("text/plain")
	@Path("/history")
    	public Response UserLogin(HttpServletRequest request, HttpServletResponse response){
		     	System.out.println("coming inside users view java class");
			  String message=null;
			  UserLoginServlet uLoginServlet =new UserLoginServlet();
			  try {
				uLoginServlet.doPost(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

				 if(Response.ok().toString()!=null) {

			
			return Response.status(200).entity(message).build();
				 }else {
		
			message = "{ \"status\" : \"error\", \n" +
					"\"message\" : \"Username or password incorrect.\" }";
			return Response.status(403).entity(message).build();
		
	
	

}
	}	
}
