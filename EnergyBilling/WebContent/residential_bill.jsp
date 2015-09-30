<%@ page import="java.sql.*,java.util.*" %>

<%! 
	Connection con;
	ResultSet rs,rs1;
	Statement st,st1;
	String cname,dname,mno,mcname;
%>
<%

con=com.DBConnection.getConnection();

	String empid = "residential";
	
st=con.createStatement();
	String jy="select * from consumer where type='"+empid+"'";
	
%>
<form action="bill1.jsp">
<table align="center" width="205">
<tr> <td width="151" align="center">  
  <font face="Franklin Gothic Medium" size="5" color="#FF0000">Meter No </font> </td> </tr>
<tr> <td width="151" align="center"> <b>Select The Meter No</b></td>
	<td width="44">
		<select name="mno">
		<%
	rs=st.executeQuery(jy);
	while(rs.next())
	{
	%>
	<option> <% out.println(rs.getString(3));%></option>
	<%
	}
	%>
	</select>
	</td>
	</tr>
	<tr><td><input type="Submit" value="Submit"></td></tr>
</table>
<table align="center" border="2">
<tr>
	<td><font face="Franklin Gothic Medium" color="#0000FF">Meter No </font> </td>
	<td><font face="Franklin Gothic Medium" color="#0000FF">Consumer Name </font> </td>
	<td><font face="Franklin Gothic Medium" color="#0000FF">Connection Type
    </font> </td>
	<td><font face="Franklin Gothic Medium" color="#0000FF">Units Used </font> </td>
	<td> <font face="Franklin Gothic Medium" color="#0000FF">Amount </font> </td>
	<td> <font face="Franklin Gothic Medium" color="#0000FF">Status</font></td>
	<td> <font face="Franklin Gothic Medium" color="#0000FF">Delete </font> </td>
</tr>
<%
try
{
	con=com.DBConnection.getConnection();
	Statement s = con.createStatement();


String emp = "residential";

	String jy1="select * from billing where type='"+emp+"'";
	
	ResultSet r = s.executeQuery(jy1);

	while(r.next())
	{
		
		 String mno = r.getString(4);
		/*String name = rs.getString(6);
		String type = rs.getString(7);
		String units = rs.getString(8);
		String amount = rs.getString(9);
		String due = rs.getString(11);*/
	%>
	<tr>
		<td><%out.println(mno);%>&nbsp;</td>
		<td><%out.println(r.getString(6));%>&nbsp;</td>
		<td><%out.println(r.getString(7));%>&nbsp;</td>
		<td><%out.println(r.getString(8));%>&nbsp;</td>
		<td><%out.println(r.getString(9));%>&nbsp;</td>
		<td><%out.println(r.getString(11));%>&nbsp;</td>
		<td><a href="delete_from_bill.jsp?mno=<%=mno%>"> Yes </a></td>
	</tr>
<%
	}
}
catch(Exception e)
{out.println(e);}%>
	