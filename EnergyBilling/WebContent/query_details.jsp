<html xmlns:v="urn:schemas-microsoft-com:vml" xmlns:o="urn:schemas-microsoft-com:office:office" xmlns="http://www.w3.org/TR/REC-html40">




<BODY bgColor=#FFFFFF>
<p>&nbsp;</p>
<p>&nbsp;</p>


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
		
String cname = request.getParameter("cname").trim();
String dname = request.getParameter("dname").trim();
String mno = request.getParameter("mno").trim();
String mcname = request.getParameter("mcname").trim();
String name = request.getParameter("name").trim();
String age = request.getParameter("query").trim();

st1=con.createStatement();



int i = st1.executeUpdate("insert into query values('"+cname+"','"+dname+"',"+mno+", '"+mcname+"','"+name+"','"+age+"')");

if(i==1)
	{
	out.println("<font color=blue size=4><b><center><b><h1>Query Added ");
			out.println("<p><h1><font color=blue><b><center><b><center>Thanks</p>");
	}




		}
		catch (Exception e)
		{
			out.println(e);
		}
	%>	