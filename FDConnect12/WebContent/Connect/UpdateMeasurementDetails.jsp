<%@page import="com.fd.Connect.ProjectServices"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
System.out.println("UpdateMeasurementDetails.jsp");
String param=request.getParameter("param");

if(param.equalsIgnoreCase("storemeasurement")){
	
	String data=request.getParameter("data");
	System.out.println("data"+data);
	ProjectServices projectServices=new ProjectServices();
	String result=projectServices.updateStoreMeasuremntDetails(data);
	//out.println(result);
	if(result.equalsIgnoreCase("DATA_NOT_UPDATED")){
		
		out.println("DATA_NOT_UPDATED");
		
		
	}else if(result.equalsIgnoreCase("DATA_INSUFFICIENT")){
		
		out.println("DATA_INSUFFICIENT");
		
	}else if(result.equalsIgnoreCase("DATA_UPDATED")){
		
		
		out.println("DATA_UPDATED");
		
	}
}
%>
