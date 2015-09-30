<%@page import="com.fd.Connect.ProjectServices"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.fd.Connect.Constants"%>
<%@page import="com.fd.Connect.DropDown"%>
<%
System.out.println("COMBO DROPDOWN BOX");
DropDown projectServices=new DropDown();
ProjectServices projectServices2=new ProjectServices();
String param=request.getParameter("param");
String dropvalue;
String outData="";
if(param.equals("projectname")){

					System.out.println("inside projectname");
					
					dropvalue=projectServices.drop_down_Projectname();
					System.out.println(dropvalue);
					out.println(dropvalue);
					
}
else if(param.equals("client")){
	
	System.out.println("inside client");
	
	dropvalue=projectServices.drop_down_client_name();
	System.out.println(dropvalue);
	out.println(dropvalue);
	
	
}
else if(param.equals("division")){
	
	System.out.println("inside division");
	
	dropvalue=projectServices.dropdown_fddiv_forsearch();
	System.out.println("dropvalue :: "+dropvalue);
	
	out.println(dropvalue);
	
	
}


%>