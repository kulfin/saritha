<%@page import="com.fd.Connect.Constants"%>
<%@page import="com.fd.Connect.ProjectServices"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%

System.out.println("DeleteMeasureData.jsp");
String checked_position=request.getParameter("checked_position");

System.out.println("checked_position--> "+checked_position);
ProjectServices projectServices=new ProjectServices();
String data_deleted=projectServices.delete_measurement_data(checked_position);
//out.println(result);
if(data_deleted.equals(Constants.DATA_DELETED)){
	out.println("MEASUREMENT SHEET DATA DELETED !!");
}else if(data_deleted.equals(Constants.DATA_NOT_DELETED)){
	out.println("MEASUREMENT SHEET DATA NOT DELETED !!");
}

%>