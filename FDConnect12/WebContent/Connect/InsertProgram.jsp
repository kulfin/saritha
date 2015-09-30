<%@page import="com.fd.Connect.ProjectServices"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%

	System.out.println("Insert Project JSP");
	String res = "";

	String data = request.getParameter("data");
	System.out.println("inside data--->" + data);
	
	String client_data = request.getParameter("client_data");
	System.out.println("inside client_data--->" + client_data);
	
	
	ProjectServices service = new ProjectServices();
	res = service.Project_insert(data,client_data);
	System.out.println("Response "+res);
	
	
	if(res.equalsIgnoreCase("DATA_NOT_INSERTED")||res.equalsIgnoreCase("")){
		
		out.println("DATA_NOT_INSERTED"+"!#!"+"");
		
	}else if(res.equalsIgnoreCase("DATA_INSUFFICIENT")){
		
		out.println("DATA_INSUFFICIENT"+"!#!"+"");
		
	}else if(res.contains("DATA_INSERTED")){
		
		System.out.println(res.substring(13));
		out.println("DATA_INSERTED"+"!#!"+res.substring(13));
		
	}
%>