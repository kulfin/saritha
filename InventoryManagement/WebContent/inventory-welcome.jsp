<%@ page language="java" %>
<%@ page session="true" import="java.sql.*,java.io.*,java.util.*"%>
<% String uid=(String)session.getAttribute("user");
if(uid==null)
{%>
<jsp:forward page="login.jsp"/>
<%}%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<head> <link rel="stylesheet" style="text/css" href="cascadess.css"/></head>
 <BODY background="images/bg6.jpg">
 <jsp:include page="projectheader.jsp"/>
  <center>WELCOME TO INVENTORY MANAGER </center> </BODY>
