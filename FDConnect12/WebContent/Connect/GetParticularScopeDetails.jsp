<%@page import="com.fd.Connect.ProjectServices"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%

String id=request.getParameter("id");
System.out.println(id);
ProjectServices project=new ProjectServices();
String result=project.getScopeDetailforScopeId(id);
out.println(result);
%>