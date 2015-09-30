<%@ page language="java" %>
<%@ page session="true" import="java.sql.*,java.io.*,java.util.*"%>
<% String uid=(String)session.getAttribute("user");
if(uid==null)
{%>
<jsp:forward page="login.jsp"/>
<%}%>
<html>
<head> <link rel="stylesheet" style="text/css" href="cascadess.css"/></head>
<body background="images/bg6.jpg">
<br><br><br><a href="change1.jsp" target="display">change password</a><br><br>
<a href="moneytransaction.jsp" target="display">total money transaction per day</a><br><br>
<a href="ViewBillsExcel.jsp" target="display">bills per day </a><br><br>
<a href="return_items.jsp" target="display">returned items</a><br><br>
<a href="Logout.jsp" target="_top">Logout</a><br>
</body>
</html>