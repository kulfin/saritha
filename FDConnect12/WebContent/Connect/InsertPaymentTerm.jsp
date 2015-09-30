<%@page import="com.fd.Connect.ProjectServices"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%

System.out.println("InsertPaymentTerm Jsp");
//retrieve parm
String args=request.getParameter("param");
String id=request.getParameter("pid");

//read param
System.out.println("Data reached insert payment-->"+args+"\n id"+id);
//send param
ProjectServices services=new ProjectServices();
String res=services.insert_payment_term(args,id);

System.out.println("Response"+res);


if(res.equalsIgnoreCase("DATA_NOT_INSERTED")){
	
	out.println("DATA_NOT_INSERTED");
	
}else if(res.equalsIgnoreCase("DATA_INSUFFICIENT")){
	
	out.println("DATA_INSUFFICIENT");
	
}else if(res.equalsIgnoreCase("DATA_INSERTED")){
	
	
	out.println("DATA_INSERTED");
	
}

%>