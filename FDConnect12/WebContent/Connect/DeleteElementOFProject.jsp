<%@page import="com.fd.Connect.Constants"%>
<%@page import="com.fd.Connect.ProjectServices"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
System.out.println("DeleteElementOFProject Jsp");

String Project_elementIDS=request.getParameter("checked_position");
System.out.println("Project_elementIDS  -->"+Project_elementIDS);

ProjectServices services=new ProjectServices();
String data_deleted=services.delete_Project_element(Project_elementIDS);

if(data_deleted.equals(Constants.DATA_DELETED)){
	out.println("DATA DELETED");
}else if(data_deleted.equals(Constants.DATA_NOT_DELETED)){
	out.println("DATA NOT DELETED");
}else if(data_deleted.equalsIgnoreCase("FOREIGN KEY CONSTRANIT FAIL")){
	out.println("FOREIGN_KEY");
}

%>