<%@ page language="java" %>
<%@ page session="true" import="java.sql.*,java.io.*,java.util.*"%>
<% String uid=(String)session.getAttribute("user");
if(uid==null)
{%>
<jsp:forward page="login.jsp"/>
<%}%>
<html><head> <link rel="stylesheet" style="text/css" href="cascadess.css"/></head>
<body background="images/bg6.jpg">
<jsp:include page="projectheader.jsp"/>
<center>wel come to sales manager</center>

</body>
</html>