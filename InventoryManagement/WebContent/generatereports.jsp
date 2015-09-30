<%@ page language="java" %>
<%@ page session="true" import="java.sql.*,java.io.*,java.util.*"%>
<%@ page import="com.DBConnection" %>
<% String uid=(String)session.getAttribute("user");
if(uid==null)
{%>
<jsp:forward page="login.jsp"/>
<%}%>
<html><head>
 <link rel="stylesheet" style="text/css" href="cascadess.css"/>

</head>
<body background="images/bg6.jpg"><center><h2>GENERATE REPORTS </h2></center><br><br>
<a href="pricewisestatus.jsp">PRICE WISE STATUS</a><br><br>
<a href="categorywisestatus.jsp">CATEGORY WISE STATUS</a><br><br>
</center>
</body>
</html>