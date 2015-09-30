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
	String cname = request.getParameter("cname");
%>
<p>&nbsp;</p>
<p>&nbsp;</p>
<form action="meter_new.jsp">
<table align="center">
<tr><td> <b><font face="Courier New" size="4">Circle Name </font></b> </td><td><input type="hidden" name="cname" value="<%out.println(cname);%>"><%out.println(cname);%></td></tr>
<tr><td width="157">
  <b><font face="Courier New" size="4">Division Name </font></b> </td>
	<td width="180">
	<select name="dname">
	<%
	st=con.createStatement();
	String jy="select * from division where cname='"+cname+"'";
	
	 rs=st.executeQuery(jy);
	

		while(rs.next())
	{%>
	<option><%out.println(rs.getString(3));%> </option>
	<%}}
		catch(Exception e)
	{
			out.println(e);
	}%></td></tr>
	<tr><td><input type="Submit" value="Submit"></td>
		</tr>
		</table>
		</form>
		</html>
		</body>