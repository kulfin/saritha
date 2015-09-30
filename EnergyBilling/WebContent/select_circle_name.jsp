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
%>
<form action="select_division_name.jsp">
<table align="center">
<tr><td colspan="2"> <b><font color="#FF0000" face="Courier New" size="5">Select 
  Circle Name</font></b><font size="5">
  </font> </td></tr>
<tr><td width="157">
  <b><font face="Courier New" size="4">Circle Name </font></b> </td>
	<td width="180">
	<select name="cname">
	<%
	st=con.createStatement();
	String jy="select * from circle ";//where idno="+empid+"";
	
	 rs=st.executeQuery(jy);
	

		while(rs.next())
	{%>
	<option><%out.println(rs.getString(2));%> </option>
	<%}}
		catch(Exception e)
	{
			out.println(e);
	}%></td></tr>
	<tr><td><input type="Submit" value="Submit"></td>
			<td></td></tr>
	</table>
	</form>


	<%
try 
{
	con=com.DBConnection.getConnection();

	String empid = request.getParameter("no");
	
st=con.createStatement();
	String jy="select * from meter";// where mno="+empid+"";
	
	
	%>
<table align="center" width="665" height="46" border="2">


<tr>
	<td width="298" align="center" height="1" dir="ltr"> <b>
    <font face="Courier New" size="4" color="#0000FF">Circle Name
  </font></b> </td>
	<td width="242" align="center" height="1" dir="ltr"> <b>
    <font face="Courier New" size="4" color="#0000FF">Division Name
  </font></b> </td>
	<td width="212" align="center" height="1" dir="ltr"> <b>
    <font face="Courier New" size="4" color="#0000FF">Meter No
  </font></b> </td>
	<td width="230" align="center" height="1" dir="rtl"> <b>
    <font face="Courier New" size="4" color="#0000FF">Company Name
  </font></b> </td>
	</tr>
	<%
	 rs=st.executeQuery(jy);
	while(rs.next())
	{
%>
	<tr>
	<td width="303" height="23" dir="ltr"> <%out.println(rs.getString(1));;%>
    &nbsp;</td>
	<td width="247" height="23" dir="ltr"> <%out.println(rs.getString(2));;%>
    &nbsp;</td>
	<td width="217" height="23" dir="ltr"> <%out.println(rs.getString(3));;%>
    &nbsp;</td>
	<td width="235" height="22" dir="ltr"> <%out.println(rs.getString(4));%>
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