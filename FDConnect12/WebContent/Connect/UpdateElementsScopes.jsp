<%@page import="com.fd.Connect.ProjectServices"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%

System.out.println("UpdateElementsScopes.jsp");

String flag=request.getParameter("flag");
System.out.println("flag--- >"+flag);
if(flag.equalsIgnoreCase("getElementData")){
	String Project_element=request.getParameter("Project_element");
	String client_name = request.getParameter("client_name");
	System.out.println("Project_element" + Project_element);
	System.out.println("client_name" + client_name);
	ProjectServices projectServices=new ProjectServices();
	String result=projectServices.getElementsForProject(Project_element);
	out.println(result);
	
}
else if(flag.equalsIgnoreCase("UpdateElement")){
	String data=request.getParameter("data");
	String id=request.getParameter("id");
	System.out.println("data:::::::::"+data +"id::::::"+id);
	ProjectServices projectServices=new ProjectServices();
	String result=projectServices.UpdateProjectElement(data,id);
	out.println(result);
}
%>