<%@ page language="java" %>
<%@ page session="true" import="java.sql.*,java.io.*,java.util.*"%>
<% String uid=(String)session.getAttribute("user");
if(uid==null)
{%>
<jsp:forward page="login.jsp"/>
<%}%>
<head> <link rel="stylesheet" style="text/css" href="cascadess.css"/></head>
<body background="images/bg6.jpg">
<marquee behavior="alternate">Copyright©IpogSoftware.com</marquee>