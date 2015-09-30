
<html>
<body>
<form action="circle_registration.jsp">
<table align="center" width="347" height="120">
<tr><td colspan="2" width="278" height="1"> 
  <p align="center"><b><font face="Monotype Corsiva" size="5" color="#FF0000">
  Circle Details </font></b> </td></tr>
<tr><td width="122" align="right" height="1" dir="ltr" bgcolor="#00FF00"> <b><font face="Courier New" size="4">No
  </font></b> </td>
	<td width="215" height="1" dir="ltr" bgcolor="#00FF00"> <input type="text" name="idno" size="5">&nbsp;
    <font color="#FF0000"><b>* numbers only</b></font></td></tr>
<tr><td width="122" align="right" height="22" dir="rtl" bgcolor="#FFFF00"> <b><font face="Courier New" size="4">Name
  </font></b> </td>
	<td width="215" height="22" dir="ltr" bgcolor="#FFFF00"> 
    <input name="name" size="20" style="float: left"></td></tr>
	<tr><td width="122" align="center" height="26"><input type="Submit" value="Submit"></td>
	<td width="215" align="center" height="26"><input type="Reset" value="Reset"></td></tr>
</table>
</form>
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
	String jy="select * from circle ";//where idno="+empid+"";
	%>
	 
<table align="center" width="347" height="43" border="2">
<tr><td width="60" align="left" height="1" dir="ltr"> <b>
  <font face="Courier New" size="4" color="#0000FF">Id No</font></b></td>
<td width="184" align="left" height="1" dir="rtl"> <b>
<font face="Courier New" size="4" color="#0000FF">Name </font></b> </td>
</tr>
<%
	rs=st.executeQuery(jy);
	while(rs.next())
	{
%>
<tr>
	<td width="153" height="23" dir="ltr"> <%out.println(rs.getString(1));;%>
    &nbsp;</td>
	<td width="277" height="22" dir="ltr"> 
    <%out.println(rs.getString(2));%>
	</td></tr>

<%
	}
	}
	catch (Exception e)
	{
		out.println(e);
	}
	%>
	
	
</table>

</body>
</html>