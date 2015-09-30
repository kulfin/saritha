<html xmlns:v="urn:schemas-microsoft-com:vml" xmlns:o="urn:schemas-microsoft-com:office:office" xmlns="http://www.w3.org/TR/REC-html40">




<BODY bgColor=#FFFFFF>


<p>&nbsp; </p>


<p><b><font face="AC" size="4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</font></b>
<font face="AC"><b></a></b></font></p>


<%@ page import="java.sql.*,java.util.*" %>

<%! 
	Connection con;
	ResultSet rs,rs1;
	Statement st,st1;
%>
<%
try 
{
	 con=com.DBConnection.getConnection();

	String uname = (String)session.getAttribute("uname");
	
st=con.createStatement();
	String jy="select * from consumer where uname='"+uname+"'";
	
	 rs=st.executeQuery(jy);
	while(rs.next())
	{
%>
<table align="center" width="347" height="90">
<tr><td colspan="2" width="278" height="1"> 
  <p align="center"><b><font face="Monotype Corsiva" size="5" color="#FF0000">
  Consumer Details
  </font></b> </td></tr>

<tr><td width="199" align="right" height="23" dir="ltr" bgcolor="#00FF00"> <b><font face="Courier New" size="4">Circle Name
  </font></b> </td>
	<td width="138" height="23" dir="ltr" bgcolor="#00FF00"> <%out.println(rs.getString(1));%></td></tr>


<tr><td width="199" align="right" height="22" dir="rtl" bgcolor="#FFFF00"> <b><font face="Courier New" size="4">Division Name</font></b> </td>
<td width="138" height="22" dir="ltr" bgcolor="#FFFF00"><%out.println(rs.getString(2));%></td></tr>

<tr><td width="199" align="right" height="23" dir="ltr" bgcolor="#00FF00"> <b><font face="Courier New" size="4">Meter No
  </font></b> </td>
	<td width="138" height="23" dir="ltr" bgcolor="#00FF00"> <%out.println(rs.getString(3));%></td></tr>

<tr><td width="199" align="right" height="22" dir="rtl" bgcolor="#FFFF00"> <b><font face="Courier New" size="4">Meter Company Name</font></b> </td>
<td width="138" height="22" dir="ltr" bgcolor="#FFFF00"><%out.println(rs.getString(4));%></td></tr>

<tr><td width="199" align="right" height="23" dir="ltr" bgcolor="#00FF00"> <b><font face="Courier New" size="4">Consumer Name
  </font></b> </td>
	<td width="138" height="23" dir="ltr" bgcolor="#00FF00"> <%out.println(rs.getString(5));%></td></tr>

<tr><td width="199" align="right" height="22" dir="rtl" bgcolor="#FFFF00"> <b><font face="Courier New" size="4">Age</font></b> </td>
<td width="138" height="22" dir="ltr" bgcolor="#FFFF00"><%out.println(rs.getString(6));%></td></tr>

<tr><td width="199" align="right" height="23" dir="ltr" bgcolor="#00FF00"> <b><font face="Courier New" size="4">Sex
  </font></b> </td>
	<td width="138" height="23" dir="ltr" bgcolor="#00FF00"> <%out.println(rs.getString(7));%></td></tr>

<tr><td width="199" align="right" height="22" dir="rtl" bgcolor="#FFFF00"> <b><font face="Courier New" size="4">Contact No</font></b> </td>
<td width="138" height="22" dir="ltr" bgcolor="#FFFF00"><%out.println(rs.getString(8));%></td></tr>

<tr><td width="199" align="right" height="23" dir="ltr" bgcolor="#00FF00"> <b><font face="Courier New" size="4">Address
  </font></b> </td>
	<td width="138" height="23" dir="ltr" bgcolor="#00FF00"> <%out.println(rs.getString(9));%></td></tr>

<tr><td width="199" align="right" height="22" dir="rtl" bgcolor="#FFFF00"> <b><font face="Courier New" size="4">Connection Type</font></b> </td>
<td width="138" height="22" dir="ltr" bgcolor="#FFFF00"><%out.println(rs.getString(10));%></td></tr>

<tr><td width="199" align="right" height="23" dir="ltr" bgcolor="#00FF00"> <b><font face="Courier New" size="4">User Name</font></b> </td>
	<td width="138" height="23" dir="ltr" bgcolor="#00FF00"> <%out.println(rs.getString(11));%></td></tr>

<tr><td width="199" align="right" height="22" dir="rtl" bgcolor="#FFFF00"> <b><font face="Courier New" size="4">Password</font></b> </td>
<td width="138" height="22" dir="ltr" bgcolor="#FFFF00"><%out.println(rs.getString(12));%></td></tr>

</table>

<%
	}
	}
	catch (Exception e)
	{
		out.println(e);
	}
	%>