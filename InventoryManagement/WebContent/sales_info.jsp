<%@ page language="java" %>
<%@ page session="true" import="java.sql.*,java.io.*,java.util.*"%>
<% String uid=(String)session.getAttribute("user");
if(uid==null)
{%>
<jsp:forward page="login.jsp"/>
<%}%>
<html>
<head><link rel="stylesheet" style="text/css" href="cascadess.css"/>
<title>Welcome to SALES PERSON</title>
</head>
<body background="images/bg6.jpg"><center>
<jsp:include page="projectheader.jsp"/>
WELCOME TO SALES PERSON</center>