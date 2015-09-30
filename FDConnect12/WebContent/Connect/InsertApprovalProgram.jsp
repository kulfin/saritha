<%@page import="com.fd.Connect.Constants"%>
<%@page import="com.fd.Connect.ProjectServices"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
String columnSeperator="#&#";
String ProjectID=request.getParameter("Project_id");
String client_approval_date=request.getParameter("client_approval_date");
String status_client=request.getParameter("status_client");
String client_approval_comment=request.getParameter("client_approval_comment");
String fd_approval_date=request.getParameter("fd_approval_date");
String status_fd=request.getParameter("status_fd");
String fd_approval_comment=request.getParameter("fd_approval_comment");

String inData=client_approval_date+columnSeperator+status_client+columnSeperator
+client_approval_comment+columnSeperator+fd_approval_date+columnSeperator+
status_fd+columnSeperator+fd_approval_comment;

ProjectServices projectServices=new ProjectServices();
String Project_approval_response=projectServices.insert_Project_approval(inData,ProjectID);
if(Project_approval_response.equalsIgnoreCase(Constants.DATA_INSERTED)){
	out.println("Project APPROVAL DATA INSERED!!");
}else if(Project_approval_response.equalsIgnoreCase(Constants.DATA_NOT_INSERTED)){
	out.println("Project APPROVAL DATA NOT INSERED!!");
	
}else if(Project_approval_response.equalsIgnoreCase(Constants.DATA_INSUFFICIENT)){
	out.println("DATA INSUFFICIENT TO INSERT !!\nPLEASE ENTER ALL DETAILS");
	
}

%>