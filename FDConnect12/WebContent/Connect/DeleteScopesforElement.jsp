<%@page import="com.fd.Connect.Constants"%>
<%@page import="com.fd.Connect.ProjectServices"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
System.out.println("DeleteStoreForProject Jsp");
String delete_project_id=request.getParameter("checked_position");

System.out.println("delete_project_id"+delete_project_id);
ProjectServices services =new ProjectServices();
String data_deleted=services.delete_scopes_for_element(delete_project_id);
if(data_deleted.equals(Constants.DATA_DELETED)){
	out.println("DATA DELETED");
}else if(data_deleted.equals(Constants.DATA_NOT_DELETED)){
	out.println("DATA NOT DELETED");
}else if(data_deleted.equals(Constants.FOREIGN_KEY_CONSTRANIT_FAIL)){
	out.println("FOREIGN_KEY_CONSTRANIT_FAIL");
}

%>