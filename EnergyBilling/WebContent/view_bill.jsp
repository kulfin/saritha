
<%@ page import="java.sql.*,java.util.*" %>

<%! 
	Connection con;
	ResultSet rs,rs1;
	Statement st,st1;
	String status;
%>
<%

con=com.DBConnection.getConnection();

String name = (String)session.getAttribute("uname");

	st=con.createStatement();
	st1 = con.createStatement();
	rs=st.executeQuery("select * from consumer where uname='"+name+"'");
	if(rs.next())
	{
		String mnumber = rs.getString(3);
		 status = "balance";
		rs1=st1.executeQuery("select * from billing where mno='"+mnumber+"' and due='"+status+"'");
		if(rs1.next())
		{
			String mno = rs1.getString(4);
			%>

<p>&nbsp;</p>
<p align="center"><font face="Franklin Gothic Medium" size="5" color="#FF0000">
Bill Payment Details</font> </p>
<form action="bill_payment.jsp">
<table align="center" >
<tr>
	<td><font face="Franklin Gothic Medium" color="#0000FF">Meter No </font> </td>
	<td><input type="hidden" name="mno" value="<%out.println(mno);%>"><%out.println(mno);%>&nbsp;</td>
	</tr>
	<tr>
	<td><font face="Franklin Gothic Medium" color="#0000FF">Consumer Name </font> </td>
	<td><%out.println(rs1.getString(6));%>&nbsp;</td></tr>
	<tr>
	<td><font face="Franklin Gothic Medium" color="#0000FF">Connection Type
    </font> </td><td><%out.println(rs1.getString(7));%>&nbsp;</td></tr>
	<tr>
	<td><font face="Franklin Gothic Medium" color="#0000FF">Units Used </font> </td>
	<td><%out.println(rs1.getString(8));%>&nbsp;</td></tr>
	<tr>
	<td> <font face="Franklin Gothic Medium" color="#0000FF">Amount </font> </td>
	<td><%out.println(rs1.getString(9));%>&nbsp;</td></tr>
	<tr>
	<td> <font face="Franklin Gothic Medium" color="#0000FF">Status</font></td>
	<td><%out.println(rs1.getString(11));%>&nbsp;</td></tr>
	<tr>
	<td> 
	<input type="hidden" name="due" value="<%out.println(status);%>">
	<font face="Franklin Gothic Medium" color="#0000FF">Card No </font> </td>
		<td><input type="text" name="cno" size="25"> <b><font color="#FF0000">* 16 Digit No</font></b></td>
		</tr>
		<tr><td colspan="2">
          <p align="center"><input type="Submit" value="Pay Bill"></td></tr>
	
<%}
	else
		{
		out.println(" Bill Paid Thanks ");
		out.println(" New Bill Will Update Shortly Thanks");
		}

}%>
</table>
</form>