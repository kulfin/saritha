<html>
<%@ page language="java"%>
<Head>
	<LINK href="cascadess.css" type="text/css" rel="stylesheet"/>

<SCRIPT LANGUAGE="JavaScript">
<!--
history.go(+1);

-->
</SCRIPT>
<Title>Reports</Title>
<body background="images/bg6.jpg">

<font face="Times New Roman" color=black >
<center>

<BR><BR><BR>

<h2 >You have successfully logged out</h2>
<BR><BR>

<!--Link to relogin again-->



<a href="login.jsp">
               <h3>click here</h3>
</A>
			   
			   to login in again</h2><br><br><h2 >Have a nice day</h2>
<%
System.out.println(session.getAttribute("user"));
session.invalidate();
//System.out.println(session.getAttribute("user"));
%>
</center>
</font>
</body>
</html>