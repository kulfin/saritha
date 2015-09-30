<html xmlns:v="urn:schemas-microsoft-com:vml" xmlns:o="urn:schemas-microsoft-com:office:office" xmlns="http://www.w3.org/TR/REC-html40">




<BODY bgColor=#FFFFFF>


<p><b><font face="AC" size="4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</font></b>
<font face="AC"><b><a style="text-decoration: none" href="consumer_details.jsp">
<font color="#000000" size="4">Back</font></a></a></b></font></p>


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

	String empid = request.getParameter("no");
	
st=con.createStatement();
	String jy="select * from consumer where mno="+empid+"";
	
	 rs=st.executeQuery(jy);
	while(rs.next())
	{
%>
<form action="consumer_update.jsp">
<table align="center" width="399">
<tr><td width="393">
<table align="center" width="347" height="90">

<tr><td colspan="2" width="278" height="1"> 
  <p align="center"><b><font face="Monotype Corsiva" size="5" color="#FF0000">
  Meter Details </font></b> </td></tr>

<tr><td width="166" align="right" height="23" dir="ltr" bgcolor="#00FF00"> <b><font face="Courier New" size="4">Circle Name
  </font></b> </td>
	<td width="171" height="23" dir="ltr" bgcolor="#00FF00"> <%cname = rs.getString(1);%>
<input type="hidden" name="cname" value="<%out.println(cname);%>"><%out.println(cname);%></td></tr>
<tr><td width="166" align="right" height="23" dir="ltr" bgcolor="#FFFF00"> <b><font face="Courier New" size="4">Division Name
  </font></b> </td>
	<td width="171" height="23" dir="ltr" bgcolor="#FFFF00"> <%dname = rs.getString(2);%>
<input type="hidden" name="dname" value="<%out.println(dname);%>"><%out.println(dname);%></td></tr>

<tr><td width="166" align="right" height="23" dir="ltr" bgcolor="#00FF00"> <b><font face="Courier New" size="4">Meter No
  </font></b> </td>
	<td width="171" height="23" dir="ltr" bgcolor="#00FF00"> <%mno = rs.getString(3);%>
<input type="hidden" name="mno" value="<%out.println(mno);%>"><%out.println(mno);%></td></tr>

<tr><td width="166" align="right" height="22" dir="rtl" bgcolor="#FFFF00"> <b><font face="Courier New" size="4">Company Name
  </font></b> </td>
	<td width="171" height="22" dir="ltr" bgcolor="#FFFF00"><%mcname = rs.getString(4);%>
<input type="hidden" name="mcname" value="<%out.println(mcname);%>"><%out.println(mcname);%>
	</td></tr>	
</table>
</td></tr>
<tr><td width="393">
<table align="center" height="199" width="664">
<tr><td colspan="2" height="10" align="center" width="501"> <b>
  <font face="Monotype Corsiva" size="5" color="#FF0000">Personal Details </font>
  </b> </td></tr>
<tr><td height="21" align="right" width="161"> <b><font face="Courier New">Name
  </font></b> </td><td height="21" width="336">
  <input type="text" name="name" size="20" value="<%out.println(rs.getString(5));%>"></td></tr>
<tr><td height="22" align="right" width="161"> <b><font face="Courier New">Age
  </font></b> </td><td height="22" width="336">
  <input type="text" name="age" size="20" value="<%out.println(rs.getString(6));%>"></td></tr>
<tr><td height="21" align="right" width="161"> <b><font face="Courier New">Sex
  </font></b> </td>
	<td height="21" width="336"> <b><font face="Courier New">Male<input type="radio" value="male" name="sex">
		Fe-Male<input type="radio" value="female" name="sex"></font></b></td>
<td height="22" align="right" width="289"><b><font face="Courier New">Contact No
  </font></b> </td><td height="22" width="216">
  <input type="text" name="cno" size="20" value="<%out.println(rs.getString(8));%>"></td></tr>

<tr><td height="22" align="right" width="161"><b><font face="Courier New">Address</font></b></td>
  <td height="22" width="336"><input type="text" name="address" size="20" value="<%out.println(rs.getString(9));%>"></td>


<td height="23" align="right" width="289"> <b><font face="Courier New">Connection Type</font></b></td>
	<td height="23" width="216"> <select name="type">
			<option>commercial</option>
			<option>residential</option>
		</select>
	</td>
</tr>

<tr><td height="22" align="right" width="161"><b><font face="Courier New">User Name</font></b></td>
  <td height="22" width="336"><input type="text" name="uname" size="20" value="<%out.println(rs.getString(11));%>"></td>

<td height="22" align="right" width="289"><b><font face="Courier New">Password</font></b></td>
  <td height="22" width="216"><input type="text" name="pass" size="20" value="<%out.println(rs.getString(12));%>"></td></tr>

<tr><td height="26" align="center" width="161"><input type="Submit" value="Submit"></td>
	<td height="26" align="center" width="336"><input type="Reset" value="Reset"></td></tr>
</table>
</td></tr>
</table>
</form>

<%
	}
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
	%>