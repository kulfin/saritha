<%@page import="com.fd.Connect.ProjectServices"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%

System.out.println("UpdatePaymentTerm.jsp");
//retrieve parm
String args=request.getParameter("dates");
String id=request.getParameter("id");

//read param
System.out.println("Data reached insert payment-->"+args+"\n id "+id);
//send param
 ProjectServices services=new ProjectServices();
String updated_payment_term=services.update_payment_term(args,id);

System.out.println("Response"+updated_payment_term);


if(updated_payment_term.equalsIgnoreCase("DATA_NOT_UPDATED")){
	
	out.println("DATA_NOT_UPDATED");
	
}else if(updated_payment_term.equalsIgnoreCase("DATA_INSUFFICIENT")){
	
	out.println("DATA_INSUFFICIENT");
	
}else if(updated_payment_term.equalsIgnoreCase("DATA_UPDATED")){
	
	
	out.println("DATA_UPDATED");	
} 

%>