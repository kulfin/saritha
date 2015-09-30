<%@ page language="java" %>
<%@ page session="true" import="java.sql.*,java.io.*,java.util.*"%>
<%@ page import="com.DBConnection" %>
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
<center><h2> Add Category Code/Category Name</h2>
<form name"f1" action="addcategory1.jsp" method="post">
<table border="0">
<tr><td>Add Category Code</td><td><input type="text" name="cc" size="8"></td></tr>
<tr><td>Add Category Name</td><td><input type="text" name="ct" size="8"></td></tr>
<tr><td><input type="submit" value="submit" ></td><td><input type="reset" value="clear"></td></tr>
</form>
</center>
</body>
</html>