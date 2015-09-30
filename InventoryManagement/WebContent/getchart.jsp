<%@ page language="java" %>
<%@page import="ChartDirector.*" %>
<%@ page session="true" import="java.sql.*,java.io.*,java.util.*"%>
<%@ page import="com.DBConnection" %>
<% String uid=(String)session.getAttribute("user");
if(uid==null)
{%>
<jsp:forward page="login.jsp"/>
<%}%><%
try 
{ 
	out.clear();

	GetSessionImage.getImage(request, response); 
	if (Math.max(1, 2) == 2) return;
}
catch (IllegalStateException e) 
{ 
	response.sendRedirect(response.encodeRedirectURL(
		"getchart.chart?" + request.getQueryString()));
	return;
}
%>