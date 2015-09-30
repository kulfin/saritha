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

	//String empid = request.getParameter("no");
	
st=con.createStatement();
	String jy="select * from circle ";//where idno="+empid+"";
	
	 rs=st.executeQuery(jy);
	
%>
<html>
<body>
<form action="division_registration.jsp">
<table align="center" width="347" height="120">
<tr><td colspan="2" width="341" height="1"> 
  <p align="center"><b><font face="Monotype Corsiva" size="5" color="#FF0000">
  Division Details </font></b> </td></tr>
<tr><td width="131" align="right" height="1" dir="ltr" bgcolor="#00FF00"> <b><font face="Courier New" size="4">No
  </font></b> </td>
	<td width="206" height="1" dir="ltr" bgcolor="#00FF00"> <input type="text" name="idno" size="5">&nbsp;
    <font color="#FF0000"><b>* numbers only</b></font></td></tr>
<tr><td width="131">
  <p align="right"><b><font face="Courier New" size="4">Circle Name </font></b> </td>
	<td width="206">
	<select name="cname">
	<%
		while(rs.next())
	{%>
	<option><%out.println(rs.getString(2));%> </option>
	<%}
}
catch(Exception w)
{
	out.println(w);
}%></td>
	</tr>

<tr><td width="131" align="right" height="22" dir="rtl" bgcolor="#FFFF00"> <b><font face="Courier New" size="4">Name
  </font></b> </td>
	<td width="206" height="22" dir="ltr" bgcolor="#FFFF00"> 
    <input name="dname" size="20" style="float: left"></td></tr>
	<tr><td width="131" align="center" height="26"><input type="Submit" value="Submit"></td>
	<td width="206" align="center" height="26"><input type="Reset" value="Reset"></td></tr>
</table>
</form>
<%
try 
{
	Connection con=com.DBConnection.getConnection();

	String empid = request.getParameter("no");
	
st=con.createStatement();
	String jy="select * from division";//f where didno="+empid+"";
	
	 
	%>
<table align="center" width="508" height="47" border="2">

<tr>
	<td width="150" align="center" height="1" dir="ltr"> <b>
    <font face="Courier New" size="4" color="#0000FF">Id No
  </font></b> </td>
<td width="224" align="center" height="1" dir="rtl"> <b>
<font face="Courier New" size="4" color="#0000FF">Circle Name
  </font></b> </td>
<td width="237" align="center" height="1" dir="rtl"> <b>
<font face="Courier New" size="4" color="#0000FF">Division Name
  </font></b> </td>
</tr>	
	<%
	rs=st.executeQuery(jy);
	while(rs.next())
	{
%>
<tr>	
	
	<td width="187" height="23" dir="ltr" align="center"> <%out.println(rs.getString(1));;%>
    &nbsp;</td>
	<td width="261" height="22" dir="ltr" align="center"> 
    <%out.println(rs.getString(2));%>
	</td>
	<td width="274" height="22" dir="ltr" align="center"> 
    <%out.println(rs.getString(3));%>
	</td>
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