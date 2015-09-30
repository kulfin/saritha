<html xmlns:v="urn:schemas-microsoft-com:vml" xmlns:o="urn:schemas-microsoft-com:office:office" xmlns="http://www.w3.org/TR/REC-html40">




<BODY bgColor=#FFFFFF>


<p><b><font face="AC" size="4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</font></b>
</p>


<%@ page import="java.sql.*,java.util.*" %>

<%! 
	Connection con;
	ResultSet rs,rs1;
	Statement st,st1;
	String cname,dname,mno,mcname;
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
<form action="query_details.jsp">
<table align="center" width="399">
<tr><td width="393">
<table align="center" width="399" height="90">

<tr><td colspan="2" width="341" height="1"> 
  <p align="center"><b><font face="Monotype Corsiva" size="5" color="#FF0000">
  Query Details </font></b> </td></tr>

<tr><td width="204" align="right" height="23" dir="ltr" bgcolor="#00FF00"> <b><font face="Courier New" size="4">Circle Name
  </font></b> </td>
	<td width="185" height="23" dir="ltr" bgcolor="#00FF00"> <%cname = rs.getString(1);%>
<input type="hidden" name="cname" value="<%out.println(cname);%>"><%out.println(cname);%></td></tr>
<tr><td width="204" align="right" height="23" dir="ltr" bgcolor="#FFFF00"> <b><font face="Courier New" size="4">Division Name
  </font></b> </td>
	<td width="185" height="23" dir="ltr" bgcolor="#FFFF00"> <%dname = rs.getString(2);%>
<input type="hidden" name="dname" value="<%out.println(dname);%>"><%out.println(dname);%></td></tr>

<tr><td width="204" align="right" height="23" dir="ltr" bgcolor="#00FF00"> <b><font face="Courier New" size="4">Meter No
  </font></b> </td>
	<td width="185" height="23" dir="ltr" bgcolor="#00FF00"> <%mno = rs.getString(3);%>
<input type="hidden" name="mno" value="<%out.println(mno);%>"><%out.println(mno);%></td></tr>

<tr><td width="204" align="right" height="22" dir="rtl" bgcolor="#FFFF00"> <b><font face="Courier New" size="4">Company Name
  </font></b> </td>
	<td width="185" height="22" dir="ltr" bgcolor="#FFFF00"><%mcname = rs.getString(4);%>
<input type="hidden" name="mcname" value="<%out.println(mcname);%>"><%out.println(mcname);%>
	</td></tr>	

<tr><td height="1" align="right" width="204"> <b><font face="Courier New">&nbsp;&nbsp;&nbsp;&nbsp; Name</font></b></td>
  <td height="1" width="185">
  <input type="hidden" name="name" size="20" value="<%String name = rs.getString(5); out.println(name);%>"><%out.println(name);%></td></tr>

<tr><td width="204"> 
  <p align="right"><font face="Courier New" size="4"><b>Description </b></font> </td>
	<td width="185"> <textarea name="query" rows="7" cols="25"></textarea></td></tr>
<tr><td height="26" align="center" width="204"><input type="Submit" value="Submit"></td>
	<td height="26" align="center" width="185"><input type="Reset" value="Reset"></td></tr>
</table>
</td></tr>
</table>
</form>

<%
	}
	}
	catch (Exception e)
	{
		out.println(e);
	}
	%>