<%@page import="com.fd.Connect.ProjectServices"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%

String scope_id=request.getParameter("scope_id");
String data=request.getParameter("data");

ProjectServices services=new ProjectServices();
String result=services.updateScopeDetails(data,scope_id);
out.println(result);

%>