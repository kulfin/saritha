<html xmlns:v="urn:schemas-microsoft-com:vml" xmlns:o="urn:schemas-microsoft-com:office:office" xmlns="http://www.w3.org/TR/REC-html40">

<%@ page import="java.sql.*,java.util.*" %>
<%@ page import="com.lowagie.text.*" %> 
<%@ page import="com.lowagie.text.pdf.*" %> 
<%@ page import="com.lowagie.text.rtf.*" %> 
<%@ page import="com.lowagie.text.html.*" %> 

<BODY bgColor=#FFFFFF>
 


<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </p>


<p><b><font face="AC" size="4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</font></b>


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
	
	String jy="select * from query";// where mno="+empid+"";
	
	 
%>
<table align="center" width="1015" height="45" border="2">

<tr>
<td width="330" align="center" height="1" dir="ltr"> <b>
<font face="Courier New" size="4" color="#0000FF">Circle Name
  </font></b> </td>
	
<td width="308" align="center" height="1" dir="rtl"> <font color="#0000FF"> <b><font face="Courier New" size="4">Division Name</font></b>
</font> </td>
<td width="199" align="center" height="1" dir="ltr"> <b>
<font face="Courier New" size="4" color="#0000FF">Meter No
  </font></b> </td>
<td width="446" align="center" height="1" dir="rtl"> <font color="#0000FF"> <b><font face="Courier New" size="4">Meter Company Name</font></b>
</font> </td>
<td width="295" align="center" height="1" dir="ltr"> <b>
<font face="Courier New" size="4" color="#0000FF">Consumer Name
  </font></b> </td>
<td width="284" align="center" height="1" dir="rtl"> <font color="#0000FF"> <b><font face="Courier New" size="4">Query</font></b>
</font> </td>
</tr>
<%
	 rs=st.executeQuery(jy);
	while(rs.next())
	{%>
<tr>
<td width="269" height="23" dir="ltr"> <%out.println(rs.getString(1));%></td>
<td width="247" height="22" dir="ltr"><%out.println(rs.getString(2));%></td>
<td width="138" height="23" dir="ltr"> <%out.println(rs.getString(3));%></td>
<td width="385" height="22" dir="ltr"><%out.println(rs.getString(4));%></td>
<td width="234" height="23" dir="ltr"> <%out.println(rs.getString(5));%></td>
<td width="223" height="22" dir="ltr"><textarea rows="3" cols="20"><%out.println(rs.getString(6));%></textarea></td>

</tr>



<%
	}
	}
	catch (Exception e)
	{
		out.println(e);
	}
	%></table>