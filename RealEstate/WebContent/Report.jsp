<!--
	File : Report.jsp
	Purpose : This jsp displays the home page containing link to change user's password
			
-->

<html>
<%@ page language="java" %>
<%@ page session="true" %>
<%@page import="java.sql.*,java.io.*,java.util.Random"%>
<SCRIPT LANGUAGE="JavaScript">
<!--
history.go(+1);

//-->
</SCRIPT>
<Head>
	<LINK href="styles.css" type="text/css" rel="stylesheet">
</head>
<jsp:include page="MultiLevelmenu.htm"/><br><br><br>
<body Class=SC>
<BR>
<Title>Reports</Title>
<font face="Times New ROman" color=blue >
<center>


<BR>


<!--Retreiving user id using Session-->

<% String Userid=(String)session.getAttribute("userr");%>

<h2 class=report>Welcome <u><%=Userid%></u> to Realtors Pro V1.0</h2><br>



</center>
</font>
<p align="right">
<A href="ChangePassword.html">Change Password </A><BR>
<%
/* Verifying wether the user is authorised to edit emp details
	if user is authorised he can edit the details if not he can
	only view the details */
	
	

%>

</p>
</body>
</html>