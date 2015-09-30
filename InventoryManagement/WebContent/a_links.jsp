<%@ page language="java" %>
<%@ page session="true" import="java.sql.*,java.io.*,java.util.*"%>
<% String uid=(String)session.getAttribute("user");
if(uid==null)
{%>
<jsp:forward page="login.jsp"/>
<%}%>
<html>
<head>
<link rel="stylesheet" style="text/css" href="cascadess.css"/>
</head>
<body background="images/bg6.jpg">
<br><br>
<table border=0 >
<tr><a href="change1.jsp" target="display">CHANGEPASSWORD</a><br><br>
<tr><a href="AddNewEmployee.jsp" target="display">ADD NEW EMPLOYEE	</a><br><br>
<tr><a href="changepriceofitem.jsp" target="display">CHANGEPRICEOFITEM</a><br><br>
<tr><a href="addcategory.jsp" target="display"> ADD CATEGORY</A><br><br>
<tr><a href="Logout.jsp" target="_top">LOGOUT</a><br><br><br>
</table>
</center>

</body>
</html>