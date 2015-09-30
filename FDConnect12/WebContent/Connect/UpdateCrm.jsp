<%@page import="com.fd.Connect.ProjectServices"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
System.out.println("Update CRM JSP");
String inData="";
String params=request.getParameter("param");
System.out.println("params --- >"+params);
String[] ajaxData=params.split("@,@");
for(int i=0;i<ajaxData.length;i++){
	inData=inData+ajaxData[i]+"#&#";
}
ProjectServices services=new ProjectServices();
String updated_crm= services.CRM_details_update(inData);


if(updated_crm.equalsIgnoreCase("DATA_NOT_UPDATED")){
	
	out.println("DATA_NOT_UPDATED");
	
	
}else if(updated_crm.equalsIgnoreCase("DATA_INSUFFICIENT")){
	
	out.println("DATA_INSUFFICIENT");
	
}else if(updated_crm.equalsIgnoreCase("DATA_UPDATED")){
	
	
	out.println("DATA_UPDATED");
	
}
%>