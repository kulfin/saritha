<%@ page import="java.sql.*,java.util.*" %>

<%! 
	Connection con;
	ResultSet rs,rs1;
	Statement st,st1;
%>
<%

con=com.DBConnection.getConnection();

	String cname = request.getParameter("cname");
	String dname = request.getParameter("dname");
%>
<html>
<body>
<form action="meter_registration.jsp">
<table align="center" width="347" height="120">
<tr><td colspan="2" width="341" height="1"> 
  <p align="center"><b><font face="Monotype Corsiva" size="5" color="#FF0000">
  Meter Details </font></b> </td></tr>

<tr><td width="157">
  <p align="right"><b><font face="Courier New" size="4">Circle Name </font></b> </td>
	<td width="180"><input type="hidden" name="cname" value="<%out.println(cname);%>">
	<%out.println(cname);%>
</td>
	</tr>
<tr><td width="157">
  <p align="right"><b><font face="Courier New" size="4">Division Name </font></b> </td>
	<td width="180"><input type="hidden" name="dname" value="<%out.println(dname);%>">
	<%out.println(dname);%>
	</td>
	</tr>

<tr><td width="157" align="right" height="1" dir="ltr" bgcolor="#00FF00"> <b><font face="Courier New" size="4">&nbsp;Meter No
  </font></b> </td>
	<td width="180" height="1" dir="ltr" bgcolor="#00FF00"> <input type="text" name="mno" size="5">&nbsp;
    <font color="#FF0000"><b>* numbers only</b></font></td></tr>

<tr><td width="157" align="right" height="22" dir="rtl" bgcolor="#FFFF00"> <b>
  <font face="Courier New" size="4">Company Name </font></b> </td>
	<td width="180" height="22" dir="ltr" bgcolor="#FFFF00"> 
    <input name="mcname" size="20" style="float: left"></td></tr>
	<tr><td width="157" align="center" height="26"><input type="Submit" value="Submit"></td>
	<td width="180" align="center" height="26"><input type="Reset" value="Reset"></td></tr>
</table>
</form>
</body>
</html>