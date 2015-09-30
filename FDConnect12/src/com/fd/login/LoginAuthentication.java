package com.fd.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fd.Connect.Service;
import com.google.gson.Gson;

/**
 * Servlet implementation class LoginAuthentication
 */
public class LoginAuthentication extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("User Name is :\t" + userName);
		System.out.println("Password is :\t" + password);
		com.aseema.fourthdimension.Service service = new  com.aseema.fourthdimension.Service();
		try{
			List result = service.ValidateUser(userName, password);
			Iterator iterator = result.iterator();
			if(result.size()>1){
				
				while(iterator.hasNext()){
					String employee_id = iterator.next().toString();
					String employeeName = iterator.next().toString();
					String role = iterator.next().toString();
					System.out.println("result[o]\t" + employeeName);
					System.out.println("result[1]\t" + role);
					HttpSession session = request.getSession();
					session.setAttribute("employee_Id", employee_id);
					session.setAttribute("Employee_Name", employeeName);
					session.setAttribute("Role", role);
					response.sendRedirect("Common/Home.jsp");
				}
			}else{
				request.setAttribute("notUser", "notUser");
				RequestDispatcher dispatch = request.getRequestDispatcher("Login.jsp");
				dispatch.forward(request, response);
//				response.sendRedirect("Login.jsp");
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
}	

	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

}
