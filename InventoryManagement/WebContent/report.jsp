<html>
<%@ page language="java" %>
<%@ page session="true" import="java.sql.*,java.io.*,java.util.*"%>
<% String uid=(String)session.getAttribute("user");
if(uid==null)
{%>
<jsp:forward page="login.jsp"/>
<%}%>
<SCRIPT LANGUAGE="JavaScript">
<!--
history.go(+1);
//-->
</SCRIPT>

<Head></head>

<br><br><br><br><br><br><BR><BR><BR>
<body>
<Title>Reports</Title>
<font face="Times New ROman" color=blue >
<center>

<!--Retreiving user id using Session-->

<% String Userid=(String)session.getAttribute("user");%>

<h3 align=left><FONT COLOR="#330000">Dear</FONT> 
<u><FONT  COLOR="#669933"><%=Userid%></FONT>
</u><FONT  COLOR="#330000">,
<BR><BR> Please navigate through the links provided</FONT></h3><br>

</center>
</font>
</body>
</html>