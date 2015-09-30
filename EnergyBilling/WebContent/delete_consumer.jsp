<%@ page import="java.sql.*,java.util.*" %>

<%! 
	Connection con;
	ResultSet rs,rs1;
	Statement st,st1;
%>

<html>
<body>
<p>&nbsp;</p>
<p align="center"><b><font face="Monotype Corsiva" size="6" color="#FF0000">

<%
con=com.DBConnection.getConnection();
	String cid = request.getParameter("no");
	st=con.createStatement();

	String jy = "delete from consumer where mno="+cid;

	 st.executeUpdate(jy);
%>
<h3 align ="center" > Deleted </h3>
<b><font size="5" face="A.C.M.E. Secret Agent">
<a style="text-decoration: none" href="consumer_details.jsp"><font color="#000000">Back</font></a></font></b></p>