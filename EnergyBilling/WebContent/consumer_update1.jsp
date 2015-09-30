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
String age = request.getParameter("age").trim();
String sex = request.getParameter("sex").trim();
String cno = request.getParameter("cno").trim();
String address = request.getParameter("address").trim();
String type = request.getParameter("type").trim();
String uname = request.getParameter("uname");
String pass = request.getParameter("pass");
	
		
		st1=con.createStatement();



int i = st1.executeUpdate("update consumer set cname='"+cname+"',dname='"+dname+"',mno="+mno+",mcname='"+mcname+"' ,name='"+name+"',age="+age+",sex='"+sex+"',cno="+cno+",address='"+address+"',type='"+type+"',uname='"+uname+"',pass='"+pass+"' where mno ="+mno+"");


if(i==1)
	{
	out.println("<font color=blue size=4><b><center><b><h1>consumer Updated ");
			out.println("<p><h1><font color=blue><b><center><b><center>Thanks</p>");
	}




		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	%>	