<%@page import="com.fd.Connect.ProjectServices"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
    String prgm_element_scope=request.getParameter("prgm_element_scope");
    String data=request.getParameter("data");
    
    System.out.println("prgm_element_scope"+prgm_element_scope);
    System.out.println("data"+data);
    
    
    ProjectServices projectServices=new ProjectServices();
    String result=projectServices.insertScopeDetailsForElement(data, prgm_element_scope);
    if(result.equalsIgnoreCase("DATA_NOT_INSERTED")){
    	
    	out.println("DATA_NOT_INSERTED");
    	
    }else if(result.equalsIgnoreCase("DATA_INSUFFICIENT")){
    	
    	out.println("DATA_INSUFFICIENT");
    	
    }else if(result.equalsIgnoreCase("DATA_INSERTED")){
    	out.println("DATA_INSERTED");

    }
    
    
    %>