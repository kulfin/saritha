<%@page import="com.aseema.fourthdimension.Service"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page language="java" %>
<%
		session.invalidate();
	//	String username=(String)session.getAttribute("username");
		HttpSession session2 = request.getSession();
		String emp_Id = (String)session2.getAttribute("employee_Id");
		if(emp_Id==null){
			response.sendRedirect("Login.jsp");
		}
		/*	
		Service service=new Service();
		service.UpdateLogOutTime(username,emp_Id);
		
		
		response.setHeader("Pragma","no-cache");  
		response.setHeader("Cache-Control","no-store");  
		response.setHeader("Expires","0");  
		response.setDateHeader("Expires",-1);
		response.sendRedirect("Login.jsp");*/
		
	
%>