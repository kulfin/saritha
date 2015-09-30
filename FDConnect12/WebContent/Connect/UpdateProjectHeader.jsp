<%@page import="com.fd.Connect.Constants"%>
<%@page import="com.fd.Connect.ProjectServices"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="ISO-8859-1"%>
<%
System.out.println("UpdateProject Header JSP");

String data=request.getParameter("data");
String Project_id = request.getParameter("Project_id");
String client_data = request.getParameter("client_data");

System.out.println("data --->"+data);
System.out.println("Project_id --->"+Project_id);
System.out.println("client_data --->"+client_data);

ProjectServices services=new ProjectServices();
String result=services.UpdateProjectHeader(data,client_data,Integer.parseInt(Project_id));

if(result.equals(Constants.DATA_UPDATED)){
	out.println("DATA UPDATED");
}else
if(result.equals(Constants.DATA_NOT_UPDATED)){
		out.println("DATA NOT UPDATED");
}else
if(result.equals(Constants.DATA_INSUFFICIENT)){
		out.println("DATA INSUFFICIENT");
}
%>