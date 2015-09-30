<%@page import="com.fd.Connect.ProjectServices"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
System.out.println("Measurement INSERT");
String flag=request.getParameter("flag");
if(flag.equalsIgnoreCase("projectElementID")){
	String ProjectStoreId =request.getParameter("Project_store_id");
	String elementID =request.getParameter("elementID");
	//System.out.println("   "+Project+"    "+elementID);
	ProjectServices projectServices=new ProjectServices();
	String result=projectServices.getProjectElementIDbyProjectElement(ProjectStoreId,elementID);
	out.println(result);
	
}
else if(flag.equalsIgnoreCase("insertStore")){
		
	String data =request.getParameter("data");	
	String visit_store_name=request.getParameter("visit_store_name");
	String Project_elementID=request.getParameter("Project_elementID");	
	
	System.out.println("data :: "+data);
	System.out.println("visit_store_name :: "+visit_store_name);
	System.out.println("Project_elementID :: "+Project_elementID);
	
	ProjectServices projectServices=new ProjectServices();
	String result=projectServices.insertMeasurementDataForElement(data,Project_elementID,visit_store_name);
	System.out.println("result   insertStore --->"+result);
	out.println(result);
	
		
}
%>