<html xmlns:v="urn:schemas-microsoft-com:vml" xmlns:o="urn:schemas-microsoft-com:office:office" xmlns="http://www.w3.org/TR/REC-html40">




<BODY bgColor=#FFFFFF>


<p><b><font face="AC" size="4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;



<%@ page import="java.sql.*,java.util.*" %>

<%! 
	Connection con;
	ResultSet rs,rs1;
	Statement st,st1;
	String cname,dname,mno,mcname,name,type;
%>
<%
try 
{
	 con=com.DBConnection.getConnection();

	String empid = request.getParameter("mno");
	
st=con.createStatement();
	String jy="select * from consumer where mno="+empid+"";
	
	 rs=st.executeQuery(jy);
	if(rs.next())
	{
%>
<form action="bill_reg.jsp">
<table align="center" width="399">
<tr><td width="393">
<table align="center" width="404" height="90">

<tr><td colspan="2" width="278" height="1"> 
  <p align="center"><b><font face="Monotype Corsiva" size="5" color="#FF0000">
  Meter Details </font></b> </td></tr>

<tr><td width="244" align="right" height="23" dir="ltr" bgcolor="#00FF00"> <b><font face="Courier New" size="4">Circle Name
  </font></b> </td>
	<td width="150" height="23" dir="ltr" bgcolor="#00FF00"> <%cname = rs.getString(1);%>
<input type="hidden" name="cname" value="<%out.println(cname);%>"><%out.println(cname);%></td></tr>
<tr><td width="244" align="right" height="23" dir="ltr" bgcolor="#FFFF00"> <b><font face="Courier New" size="4">Division Name
  </font></b> </td>
	<td width="150" height="23" dir="ltr" bgcolor="#FFFF00"> <%dname = rs.getString(2);%>
<input type="hidden" name="dname" value="<%out.println(dname);%>"><%out.println(dname);%></td></tr>

<tr><td width="244" align="right" height="23" dir="ltr" bgcolor="#00FF00"> <b><font face="Courier New" size="4">Meter No
  </font></b> </td>
	<td width="150" height="23" dir="ltr" bgcolor="#00FF00"> <%mno = rs.getString(3);%>
<input type="hidden" name="mno" value="<%out.println(mno);%>"><%out.println(mno);%></td></tr>

<tr><td width="244" align="right" height="22" dir="rtl" bgcolor="#FFFF00"> <b><font face="Courier New" size="4">Company Name
  </font></b> </td>
	<td width="150" height="22" dir="ltr" bgcolor="#FFFF00"><%mcname = rs.getString(4);%>
<input type="hidden" name="mcname" value="<%out.println(mcname);%>"><%out.println(mcname);%>
	</td></tr>	


<tr><td width="244" align="right" height="22" dir="rtl" bgcolor="#FFFF00"> <b><font face="Courier New" size="4">Consumer Name
  </font></b> </td>
	<td width="150" height="22" dir="ltr" bgcolor="#FFFF00"><%name = rs.getString(5);%>
<input type="hidden" name="name" value="<%out.println(name);%>"><%out.println(name);%>
	</td></tr>	

<tr><td width="244" align="right" height="22" dir="rtl" bgcolor="#FFFF00"> <b><font face="Courier New" size="4">
  Connection type
  </font></b> </td>
	<td width="150" height="22" dir="ltr" bgcolor="#FFFF00"><%type = rs.getString(10);%>
<input type="hidden" name="type" value="<%out.println(type);%>"><%out.println(type);%>
	</td></tr>

	<tr><td width="244" align="right" height="22" dir="rtl" bgcolor="#FFFF00"> <b><font face="Courier New" size="4">No Of Units Used </td>
		<td width="150" height="22" dir="ltr" bgcolor="#FFFF00"> 
        <input type="text" name="used" size="13"></td>
	</tr>
</font></b>
<tr><td> 
  <p align="center"> <input type="submit" value="Submit"></td>
	<td></td>
	</tr>
</font></b>
<%
	}
	}
	catch (Exception e)
	{
		out.println(e);
	}
	%>