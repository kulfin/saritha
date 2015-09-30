<%@page import="com.fd.Connect.ProjectServices"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%

System.out.println(" InsertJobCard Jsp"); 

String flag=request.getParameter("flag");
if(flag.equalsIgnoreCase("submitJobCard")){
	
	String data=request.getParameter("data");
	
	ProjectServices projectServices=new ProjectServices();
	String res=projectServices.insertJobCard(data);
	System.out.println(res);
	

	
	if(res.equalsIgnoreCase("DATA_NOT_INSERTED")){
		
		out.println("DATA NOT INSERTED");
		
	}else if(res.equalsIgnoreCase("DATA_INSUFFICIENT")){
		
		out.println("DATA INSUFFICIENT");
		
	}else if(res.equalsIgnoreCase("DATA_INSERTED")){

		out.println("DATA INSERTED");
		
	}
	else if(res.equalsIgnoreCase("DUPLICATE JOB CARD")){
		out.println("DUPLICATE JOB CARD");
	}
	
	else if(res.equalsIgnoreCase("NO_MEASUREMENT_DATA_FOR_STORE")){
		out.println("NO MEASUREMENT DATA FOR STORE");
	}
	
}
else if(flag.equalsIgnoreCase("checkJobCard")){
	
	String data=request.getParameter("data");
	
	ProjectServices projectServices=new ProjectServices();
	String result=projectServices.checkJobCardDetail(data);
	out.println(result);
	
}



%>