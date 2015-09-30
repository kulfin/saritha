<html xmlns:v="urn:schemas-microsoft-com:vml" xmlns:o="urn:schemas-microsoft-com:office:office" xmlns="http://www.w3.org/TR/REC-html40">




<BODY bgColor=#FFFFFF>


<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </p>


<p>&nbsp;</p>


<p><b><font face="AC" size="4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</font></b>
<font face="AC"><b><a style="text-decoration: none" href="meter_details.jsp">
<font color="#000000" size="4">Back</font></a></a></b></font></p>


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

	String empid = request.getParameter("no");
	
st=con.createStatement();
	String jy="select * from meter where mno="+empid+"";
	
	 rs=st.executeQuery(jy);
	while(rs.next())
	{
%>
<table align="center" width="347" height="90">

<tr><td colspan="2" width="278" height="1"> 
  <p align="center"><b><font face="Monotype Corsiva" size="5" color="#FF0000">
  Meter Details </font></b> </td></tr>

<tr><td width="166" align="right" height="23" dir="ltr" bgcolor="#00FF00"> <b><font face="Courier New" size="4">Circle Name
  </font></b> </td>
	<td width="171" height="23" dir="ltr" bgcolor="#00FF00"> <%out.println(rs.getString(1));;%>
    &nbsp;</td></tr>
<tr><td width="166" align="right" height="23" dir="ltr" bgcolor="#FFFF00"> <b><font face="Courier New" size="4">Division Name
  </font></b> </td>
	<td width="171" height="23" dir="ltr" bgcolor="#FFFF00"> <%out.println(rs.getString(2));;%>
    &nbsp;</td></tr>

<tr><td width="166" align="right" height="23" dir="ltr" bgcolor="#00FF00"> <b><font face="Courier New" size="4">Meter No
  </font></b> </td>
	<td width="171" height="23" dir="ltr" bgcolor="#00FF00"> <%out.println(rs.getString(3));;%>
    &nbsp;</td></tr>

<tr><td width="166" align="right" height="22" dir="rtl" bgcolor="#FFFF00"> <b><font face="Courier New" size="4">Company Name
  </font></b> </td>
	<td width="171" height="22" dir="ltr" bgcolor="#FFFF00"> 
    <%out.println(rs.getString(4));%>
	</td></tr>

	
</table>

<%
	}
	}
	catch (Exception e)
	{
		out.println(e);
	}
	%>